package com.zzh.estore.mapper;

import com.zzh.estore.entity.District;
import com.zzh.estore.service.IAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/12 10:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for (District d : list){
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode() {
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
