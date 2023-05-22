package com.yanhuo.product;

import com.yanhuo.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallProductApplicationTests {

	@Autowired
	BrandService brandService;
	@Test
	void contextLoads() {
		int count = brandService.count() ;
		System.out.println(count);
	}

}
