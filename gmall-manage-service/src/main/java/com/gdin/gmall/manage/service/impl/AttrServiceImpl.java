package com.gdin.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gdin.gmall.bean.PmsBaseAttrInfo;
import com.gdin.gmall.bean.PmsBaseAttrValue;
import com.gdin.gmall.bean.PmsBaseSaleAttr;
import com.gdin.gmall.bean.PmsProductInfo;
import com.gdin.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.gdin.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.gdin.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.gdin.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrInfoValueMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> attrInfoList=pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);

        for (PmsBaseAttrInfo baseAttrInfo:attrInfoList) {
            List<PmsBaseAttrValue> pmsBaseAttrValues = new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            pmsBaseAttrValues = pmsBaseAttrInfoValueMapper.select(pmsBaseAttrValue);
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }

        return attrInfoList;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        try {
            String id = pmsBaseAttrInfo.getId();
            if (StringUtils.isBlank(id)) {
                pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
                List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
                for (PmsBaseAttrValue baseAttrValue : attrValueList) {
                    baseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    pmsBaseAttrInfoValueMapper.insertSelective(baseAttrValue);
                }
            }else{
                Example example = new Example(PmsBaseAttrInfo.class);
                example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
                pmsBaseAttrInfoMapper.updateByExample(pmsBaseAttrInfo,example);

                PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
                pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrInfoValueMapper.delete(pmsBaseAttrValueDel);

                List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
                for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    pmsBaseAttrInfoValueMapper.insertSelective(pmsBaseAttrValue);
                }
            }

            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "failure ";
        }
    }

    @Override
    public List<PmsBaseAttrValue> attrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrInfoValueMapper.select(pmsBaseAttrValue);
        return pmsBaseAttrValues;
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        PmsBaseSaleAttr pmsBaseSaleAttr = new PmsBaseSaleAttr();
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrMapper.select(pmsBaseSaleAttr);
        return pmsBaseSaleAttrs;
    }
}
