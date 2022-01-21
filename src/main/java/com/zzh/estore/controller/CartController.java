package com.zzh.estore.controller;

import com.zzh.estore.entity.Cart;
import com.zzh.estore.service.ICartService;
import com.zzh.estore.util.JsonResult;
import com.zzh.estore.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/14 16:37
 */
@RestController
@RequestMapping("cart")
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,
                                      Integer amount,
                                      HttpSession session) {
        cartService.addToCart(getUidFromSession(session),
                              pid,
                              amount,
                              getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> list = cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<>(OK, list);
    }

    @RequestMapping("{cid}/add")
    public JsonResult<Integer> addNum(@PathVariable Integer cid, HttpSession session) {
        Integer num = cartService.addNum(cid,
                           getUidFromSession(session),
                           getUsernameFromSession(session));
        return new JsonResult<>(OK, num);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session) {
        List<CartVO> list = cartService.getVOByCid(getUidFromSession(session),cids);
        return new JsonResult<>(OK,list);

    }
}
