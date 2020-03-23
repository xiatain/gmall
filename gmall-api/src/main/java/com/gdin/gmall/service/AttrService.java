package com.gdin.gmall.service;

import com.gdin.gmall.bean.PmsBaseAttrInfo;
import com.gdin.gmall.bean.PmsBaseAttrValue;
import com.gdin.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> attrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
