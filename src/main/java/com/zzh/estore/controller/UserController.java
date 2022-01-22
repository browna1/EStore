package com.zzh.estore.controller;

import com.zzh.estore.controller.ex.*;
import com.zzh.estore.entity.User;
import com.zzh.estore.service.IUserService;
import com.zzh.estore.util.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/8 11:24
 */
@Api(tags = "用户")
@RestController //等于@Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型接受前端数据
     * springboot会将前端url地址中的参数名和pojo类的属性名进行比较
     * 如果名称相同，则将值注入到pojo类中对应的属性上
     */
    @RequestMapping("register")
    public JsonResult<Void> register(User user){
        userService.register(user);
        return new JsonResult<>(OK);
//传统方法        JsonResult<Void> result = new JsonResult<>();
//        try {
//            userService.register(user);
//            result.setStatus(200);
//            result.setMessage("注册成功");
//        } catch (UsernameDuplicatedException e) {
//            result.setStatus(4000);
//            result.setMessage("用户已存在");
//        } catch (InsertException e) {
//            result.setStatus(5000);
//            result.setMessage("注册时产生异常");
//        }
//        return result;
    }
    /**
     * 2.接收数据方式：请求处理方法的参数列表设置为非pojo类型
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        // 向全局的session中绑定数据
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        // 获取session中绑定的数据
//        System.out.println(getUidFromSession(session));
//        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("getInfo_by_uid")
    public JsonResult<User> getInfoByUid(HttpSession session) {
        User data =
                userService.getInfoByUid(getUidFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,
                                       HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<Void>(OK);
    }

    // 上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
    // 限制上传文件的类型
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
        AVATAR_TYPES.add("image/webp");
    }

    /**
     * MultipartFile接口时SpringMVC提供的接口，包装了获取文件类型的数据
     * 只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数
     * SpringBoot自动将传递给服务器的文件赋值给这个参数
     * @RequestParam 表示请求中的参数 用它处理前后端名称不一致
     * @param session
     * @param file
     * @return
     */
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userService.changeAvatar(uid, username, avatar);
        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
    }
}
