package com.zzh.estore.service;

import com.zzh.estore.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/11 16:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void addAddress(){
        Address address = new Address();
        address.setPhone("15230351537");
        address.setName("麻子");
        iAddressService.addNewAddress(15, "admin", address);
    }

    @Test
    public void setDefault() {
        iAddressService.setDefault(12,15,"致远");
    }

    @Test
    public void deleteByAid() {
        iAddressService.deleteAddress(11,15,"aj");
    }
}
