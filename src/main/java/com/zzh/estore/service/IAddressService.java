package com.zzh.estore.service;

import com.zzh.estore.entity.Address;

import java.util.List;

/**
 * @author ：zzh
 * @description ：收货地址业务层接口
 * @date ：Created in 2022/1/11 15:52
 */
public interface IAddressService {

    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 根据用户id获取地址
     * @param uid 用户id
     * @return 该用户所有收货地址
     */
    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 根据地址id删除地址数据
     * @param aid 地址id
     */
    void deleteAddress(Integer aid,Integer uid, String username);

    /**
     *
     * @param aid
     * @return
     */
    Address getByAid(Integer aid,Integer uid);
}
