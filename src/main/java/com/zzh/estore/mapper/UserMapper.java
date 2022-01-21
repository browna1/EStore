package com.zzh.estore.mapper;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/6 14:00
 */

import com.zzh.estore.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/** 用户模块的持久层接口 */

public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数（增删改）
     */
    Integer insert(User user);

    /**
     * 根据用户名查询数据
     * @param username 用户名
     * @return 用户数据
     */
    User findByUsername(String username);

    /**
     * 根据id修改密码
     * @param uid 用户id
     * @param password 用户新密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 根据id查询用户数据
     * @param uid 用户id
     * @return 找到则返回用户数据，否则返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户信息
     * @param user 用户
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * @Param("SQL映射文件中#{}占位符的变量名")：解决的问题，当SQL语句的占位符
     * 和映射的接口方法参数名不一致时，可以强行注入到占位符变量上
     * 根据用户id修改用户头像
     * @param uid id
     * @param avatar 头像路径
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
