package com.zzh.estore.service;

import com.zzh.estore.entity.Address;
import com.zzh.estore.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/11 16:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        Integer[] cids = {3,5};
        Order order = orderService.create(17,15,"阿静",cids);
        System.out.println(order);
    }
}
