package com.yanhuo.coupon;

import com.yanhuo.coupon.service.HomeAdvService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallCouponApplicationTests {

    @Autowired
    HomeAdvService homeAdvService;

    @Test
    void contextLoads() {
        int count = homeAdvService.count();
        System.out.println(count);
    }

}
