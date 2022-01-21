package com.zzh.estore.service.impl;

import com.zzh.estore.entity.Address;
import com.zzh.estore.entity.Order;
import com.zzh.estore.entity.OrderItem;
import com.zzh.estore.mapper.OrderMapper;
import com.zzh.estore.service.IAddressService;
import com.zzh.estore.service.ICartService;
import com.zzh.estore.service.IOrderService;
import com.zzh.estore.service.ex.InsertException;
import com.zzh.estore.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/18 14:38
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        // 即将要下单的列表
        List<CartVO> list = cartService.getVOByCid(uid,cids);
        // 计算商品总价
        long totalPrice = 0L;

        Address address = addressService.getByAid(aid,uid);

        Order order = new Order();
        order.setUid(uid);
        // 收货地址数据
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 支付、总价、时间
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        // 日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        // 查询数据
        Integer row = orderMapper.insertOrder(order);
        if(row != 1) {
            throw new InsertException("插入订单异常");
        }
        for (CartVO vo : list) {
            totalPrice += vo.getRealPrice()*vo.getNum();
            // 创建订单详细项
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(vo.getPid());
            orderItem.setTitle(vo.getTitle());
            orderItem.setPrice(vo.getRealPrice());
            orderItem.setImage(vo.getImage());
            orderItem.setNum(vo.getNum());
            // 日志字段
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            row = orderMapper.insertOrderItem(orderItem);
            if (row != 1) {
                throw new InsertException("插入订单项异常");
            }
        }
        return order;
    }
}
