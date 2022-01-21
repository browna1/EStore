package com.zzh.estore.mapper;

import com.zzh.estore.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/14 15:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(15);
        cart.setPid(10000011);
        cart.setNum(3);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1,5,"致远",new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(15,10000011);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid() {
        System.out.println(cartMapper.findVOByUid(15));
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(5));
    }

    @Test
    public void findVOByCid() {
        Integer[] cids = {1,2,3,4,21,32,7};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
