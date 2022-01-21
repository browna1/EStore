package com.zzh.estore.mapper;

import com.zzh.estore.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/11 15:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(17);
        address.setPhone("15230888387");
        address.setName("言哥");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer cnt = addressMapper.countByUid(16);
        System.out.println(cnt);
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(15);
        System.out.println(list);
    }

    @Test
    public void findByAid() {
        Address address = addressMapper.findByAid(3);
        System.out.println(address);
    }
    @Test
    public void updateNonDefault() {
        addressMapper.updateNonDefault(15);
    }
    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(4,"小哥",new Date());
    }
    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(6);
    }
    @Test
    public void findLastModified() {
        System.out.println(addressMapper.findLastModified(15));
    }
}
