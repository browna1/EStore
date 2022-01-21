package com.zzh.estore.mapper;

import com.zzh.estore.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/6 15:06
 */
@SpringBootTest
// 自己定义的测试方法必须写
@RunWith(SpringRunner.class)
public class UserMapperTests {

    // idea有检测的功能，接口不能直接创建Bean
    @Autowired
    private UserMapper userMapper;
    /**
     * 必须被test注解修饰
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("阿静");
        user.setPassword("1068");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("阿静");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(5,"123",
                "管理员", new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(5));
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(14);
        user.setPhone("15230888387");
        user.setEmail("114@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(
                15,
                "/upload/avatar.png",
                "admin",
                 new Date());
    }
}
