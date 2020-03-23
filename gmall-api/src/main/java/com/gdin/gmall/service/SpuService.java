package com.gdin.gmall.service;

import com.gdin.gmall.bean.PmsProductImage;
import com.gdin.gmall.bean.PmsProductInfo;
import com.gdin.gmall.bean.PmsProductSaleAttr;
import com.gdin.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String spuId, String skuId);

}
