package com.gdin.gmall.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gdin.gmall.bean.PmsSearchSkuInfo;
import com.gdin.gmall.bean.PmsSkuInfo;
import com.gdin.gmall.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallSearchServiceApplicationTests {

	@Reference
	SkuService skuService;
	@Autowired
	JestClient jestClient;

	@Test
	public void contextLoads() throws IOException {
		List<PmsSkuInfo> pmsSkuInfoList = new ArrayList<>();
		pmsSkuInfoList=skuService.getAllSku("61");
		List<PmsSearchSkuInfo> pmsSearchSkuInfoList = new ArrayList<>();
		for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
			PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo();
			BeanUtils.copyProperties(pmsSkuInfo,pmsSearchSkuInfo);
			pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
		}
		for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfoList) {
			Index put = new Index.Builder(pmsSearchSkuInfo).index("gmall").type("PmsSkuInfo").id(pmsSearchSkuInfo.getId()+"").build();
			jestClient.execute(put);
		}
	}

}
