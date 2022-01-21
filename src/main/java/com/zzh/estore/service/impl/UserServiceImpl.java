package com.zzh.estore.service.impl;

import com.zzh.estore.entity.User;
import com.zzh.estore.mapper.UserMapper;
import com.zzh.estore.service.IUserService;
import com.zzh.estore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author ：zzh
 * @description ：实现用户模块业务层
 * @date ：Created in 2022/1/7 12:06
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // 获得要注册的用户名字
        String username = user.getUsername();
        // 调用findByUsername  判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        // 结果为null 可以注册
        if (result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        String oldPwd = user.getPassword();
        // 随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Pwd = getMD5Pwd(oldPwd,salt);
        // 将加密之后的密码给到user对象中
        user.setPassword(md5Pwd);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        // 执行注册业务
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("注册过程中产生异常");
        }
    }
    private String getMD5Pwd(String password, String salt) {
        // md5加密算法方法的调用  进行三次加密
        for (int i=0;i<3;i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户是否存在 不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        // 检测密码是否匹配
        // 1.先获取数据库中加密之后的密码
        String oldPwd = result.getPassword();
        // 2.和用户传递过来的密码进行比较
        //     先获取盐值：上一次注册时生成的盐值
        String salt = result.getSalt();
        //     将密码按照相同md5规则进行加密
        String newMd5Pwd = getMD5Pwd(password, salt);
        //     比较密码
        if (!newMd5Pwd.equals(oldPwd)) {
            throw new PasswordNotMatchException("密码错误");
        }

        // 判断is_delete是否为1 若是则表示用户已注销
        if (result.getIsDelete() == 1){
            throw new UserNotFoundException("用户已注销");
        }

        // 查询用户数据，只取需要的字段，能提升性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        // 比较原始密码是否输入正确
        String oldMd5Pwd =
                getMD5Pwd(oldPassword, result.getSalt());
        if (!result.getPassword().contentEquals(oldMd5Pwd)){
            throw new PasswordNotMatchException("原始密码输入错误");
        }
        // 将新密码加密后修改
        String newMd5Pwd =
                getMD5Pwd(newPassword, result.getSalt());
        Integer row =
                userMapper.updatePasswordByUid(uid, newMd5Pwd,username, new Date());
        if (row != 1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }
    // 获取个人资料界面的用户信息
    @Override
    public User getInfoByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
//        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer row = userMapper.updateInfoByUid(user);
        if (row != 1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String modifiedUser, String avatar) {
        // 查询当前用户是否存在
        User result = userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer row = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, new Date());
        if (row != 1){
            throw new UpdateException("更新头像时产生未知异常");
        }
    }
}
