package com.zzh.estore.mapper;

import com.zzh.estore.entity.Address;
import com.zzh.estore.entity.Order;
import com.zzh.estore.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/11 15:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(15);
        order.setRecvName("阿静");
        order.setRecvPhone("15230888387");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(1000003);
        orderItem.setTitle("广播打电脑");
        orderMapper.insertOrderItem(orderItem);
    }
}
