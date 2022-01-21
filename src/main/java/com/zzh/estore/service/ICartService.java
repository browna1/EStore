package com.zzh.estore.service;

import com.zzh.estore.vo.CartVO;

import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/14 16:03
 */
public interface ICartService {

    /**
     * 将商品添加到购物车
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增的商品数量
     * @param username 修改者
     */
    void addToCart(Integer uid,
                   Integer pid,
                   Integer amount,
                   String username);

    List<CartVO> getVOByUid(Integer uid);

    /**
     * 更新购物车中商品数量
     * @param cid
     * @param uid
     * @param username
     * @return 修改后新数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCid(Integer uid, Integer[] cids);
}
