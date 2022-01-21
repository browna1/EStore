package com.zzh.estore.service;

import com.zzh.estore.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/7 12:05
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户对象
     */
    void register(User user);

    /**
     * 用户登录方法
     * @param username  用户名
     * @param password  密码
     * @return 该用户数据，没有返回null
     */
    User login(String username, String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPwd,
                        String newPwd);

    /**
     * 根据用户id查询用户信息
     * @param uid id
     * @return 用户信息
     */
    User getInfoByUid(Integer uid);

    /**
     * 更新用户信息
     * @param uid id
     * @param username 用户名
     * @param user 用户对象
     */
    void changeInfo(Integer uid,
                    String username,
                    User user);

    /**
     * 修改用户头像
     * @param uid 用户id
     * @param avatar 头像路径
     * @param modifiedUser 修改人
     */
    void changeAvatar(Integer uid,
                      String avatar,
                      String modifiedUser);
}
