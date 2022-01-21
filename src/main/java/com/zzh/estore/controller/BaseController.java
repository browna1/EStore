package com.zzh.estore.controller;

import com.zzh.estore.controller.ex.*;
import com.zzh.estore.entity.User;
import com.zzh.estore.service.ex.*;
import com.zzh.estore.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author ：zzh
 * @description ：控制层类的基类
 * @date ：Created in 2022/1/8 11:36
 */
public class BaseController {
    // 操作成功
    public static final int OK = 200;

    // 请求处理方法  返回值是给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当前项目中产生异常，被统一拦截到此方法中，这个方法充当请求处理方法
    @ExceptionHandler({ServiceException.class, FileUploadException.class})  //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setStatus(4000);
            result.setMessage("该用户已存在");
        } else if(e instanceof UserNotFoundException){
            result.setStatus(4001);
            result.setMessage("用户不存在异常");
        } else if(e instanceof PasswordNotMatchException){
            result.setStatus(4002);
            result.setMessage("用户密码错误异常");
        } else if(e instanceof AddressCountLimitException){
            result.setStatus(4003);
            result.setMessage("收货地址数量超额异常");
        } else if(e instanceof AddressNotFoundException) {
            result.setStatus(4004);
            result.setMessage("收货地址数据不存在异常");
        } else if(e instanceof AccessDeniedException) {
            result.setStatus(4005);
            result.setMessage("收货地址数据非法访问异常");
        } else if (e instanceof ProductNotFoundException) {
            result.setStatus(4006);
        } else if (e instanceof CartNotFoundException) {
            result.setStatus(4007);
        } else if(e instanceof InsertException){
            result.setStatus(5000);
            result.setMessage("插入数据时产生未知异常");
        } else if(e instanceof UpdateException){
            result.setStatus(5001);
            result.setMessage("更新数据时产生未知异常");
        } else if (e instanceof FileEmptyException) {
            result.setStatus(6000);
        } else if (e instanceof FileSizeException) {
            result.setStatus(6001);
        } else if (e instanceof FileTypeException) {
            result.setStatus(6002);
        } else if (e instanceof FileStateException) {
            result.setStatus(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setStatus(6004);
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前用户的uid
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    /**
     * 获取session对象中的用户名
     * @param session session对象
     * @return 当前用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
