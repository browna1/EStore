package com.zzh.estore.service;

import com.zzh.estore.entity.Address;
import com.zzh.estore.entity.Order;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/18 14:21
 */
public interface IOrderService {
    /**
     * 创建订单的抽象方法
     * @param aid
     * @param uid
     * @param username
     * @param cids
     * @return
     */
    Order create(Integer aid, Integer uid, String username, Integer[] cids);
}
