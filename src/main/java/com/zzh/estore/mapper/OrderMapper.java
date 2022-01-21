package com.zzh.estore.mapper;

import com.zzh.estore.entity.Order;
import com.zzh.estore.entity.OrderItem;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/18 13:56
 */
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项的数据
     * @param orderItem 订单项数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
