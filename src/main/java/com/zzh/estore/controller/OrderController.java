package com.zzh.estore.controller;

import com.zzh.estore.entity.Order;
import com.zzh.estore.service.IOrderService;
import com.zzh.estore.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/18 16:24
 */
@Api(tags = "操作订单数据")
@RestController
@RequestMapping("order")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        Integer uid = getUidFromSession(session);
        String usernames = getUsernameFromSession(session);
        Order data = orderService.create(aid,uid,usernames,cids);
        return new JsonResult<Order>(OK,data);
    }

}
