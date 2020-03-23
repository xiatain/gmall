package com.gdin.gmall.service;

import com.gdin.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String spuId);

    List<PmsSkuInfo> getAllSku(String catalog3Id);
}
