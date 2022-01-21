package com.zzh.estore.service;

import com.zzh.estore.entity.User;
import com.zzh.estore.mapper.UserMapper;
import com.zzh.estore.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/6 15:06
 */
@SpringBootTest
// 自己定义的测试方法必须写
@RunWith(SpringRunner.class)
public class UserServiceTests {

    // idea有检测的功能，接口不能直接创建Bean
    @Autowired
    private IUserService iUserService;
    /**
     * 必须被test注解修饰
     */
    @Test
    public void register(){
        try {
            User user = new User();
            user.setUsername("ajjj");
            user.setPassword("1068");
            iUserService.register(user);
            System.out.println("注册完了");
        } catch (ServiceException e) {
            // 获取类的对象 然后获取类名
            System.out.println(e.getClass().getSimpleName());
            // 输出异常信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User user = iUserService.login("john", "1235");
        System.out.println(user);
    }
    @Test
    public void changePassword(){
        iUserService.changePassword(13, "admin", "456", "haha");
    }

    @Test
    public void getInfoByUid(){
        System.out.println(iUserService.getInfoByUid(14));
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("19858179927");
        user.setEmail("1068@qq.com");
        user.setGender(0);
        iUserService.changeInfo(14, "admin", user);
    }

    @Test
    public void changeAvatar(){
        iUserService.changeAvatar(15, "/upload/test.png", "金泽");
    }
}
