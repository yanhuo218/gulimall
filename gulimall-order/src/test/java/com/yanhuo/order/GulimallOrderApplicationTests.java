package com.yanhuo.order;

import com.yanhuo.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallOrderApplicationTests {

    @Autowired
    OrderService orderService;
    @Test
    void contextLoads() {
        int count = orderService.count();
        System.out.println(count);
    }

}
