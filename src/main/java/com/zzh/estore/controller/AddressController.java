package com.zzh.estore.controller;

import com.zzh.estore.entity.Address;
import com.zzh.estore.service.IAddressService;
import com.zzh.estore.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/12 9:17
 */
@Api(tags = "操作收货地址")
@RestController
@RequestMapping("address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    // Restful风格的请求
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,
                                       HttpSession session) {
        addressService.setDefault(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteByAid(@PathVariable("aid") Integer aid,
                                        HttpSession session) {
        addressService.deleteAddress(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
