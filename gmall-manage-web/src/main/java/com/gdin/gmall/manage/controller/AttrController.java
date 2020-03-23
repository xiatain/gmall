package com.gdin.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gdin.gmall.bean.PmsBaseAttrInfo;
import com.gdin.gmall.bean.PmsBaseAttrValue;
import com.gdin.gmall.bean.PmsBaseSaleAttr;
import com.gdin.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {


    @Reference
    AttrService attrService;

    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        String status = attrService.saveAttrInfo(pmsBaseAttrInfo);
        return status;
    }

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getattrInfoList(String catalog3Id) {
        List<PmsBaseAttrInfo> attrInfoList= attrService.attrInfoList(catalog3Id);
        return attrInfoList;
    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        List<PmsBaseAttrValue> attrValueList= attrService.attrValueList(attrId);
        return attrValueList;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> getbaseSaleAttrList() {
        List<PmsBaseSaleAttr> baseSaleAttrList= attrService.baseSaleAttrList();
        return baseSaleAttrList;
    }
}
