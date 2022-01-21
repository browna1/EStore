package com.zzh.estore.service.impl;

import com.zzh.estore.entity.Cart;
import com.zzh.estore.entity.Product;
import com.zzh.estore.mapper.CartMapper;
import com.zzh.estore.mapper.ProductMapper;
import com.zzh.estore.service.ICartService;
import com.zzh.estore.service.ex.AccessDeniedException;
import com.zzh.estore.service.ex.CartNotFoundException;
import com.zzh.estore.service.ex.InsertException;
import com.zzh.estore.service.ex.UpdateException;
import com.zzh.estore.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/14 16:12
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid,
                          Integer pid,
                          Integer amount,
                          String username) {
        // 查询当前要添加的这个商品是否已存在
        Cart result = cartMapper.findByUidAndPid(uid,pid);
        Date date = new Date();
        if (result == null) { // 这个商品从来没有被添加到购物车中，所以插入
            // 创建一个cart对象
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 价格来自商品中的数据
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());

            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            Integer row = cartMapper.insert(cart);
            if (row != 1) {
                throw new InsertException("插入购物车数据时产生未知异常");
            }
        } else { // 当前商品已经存在，所以更新数量
            Integer num = result.getNum()+amount;
            Integer row = cartMapper.updateNumByCid(result.getCid(),
                                      num,
                                      username,
                                      date);
            if (row != 1) {
                throw new UpdateException("更新购物车数据时产生未知异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("数据不存在");
        }

        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer row = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (row != 1) {
            throw new UpdateException("更新购物车数据时产生未知异常");
        }

        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            CartVO cartVO = iterator.next();
            if (!cartVO.getUid().equals(uid)) {
                list.remove(cartVO);
            }
        }

        return list;
    }
}
