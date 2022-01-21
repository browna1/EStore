package com.zzh.estore.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：zzh
 * @description ：定义一个登录拦截器
 * @date ：Created in 2022/1/9 12:40
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session中是否有uid，如果有则放行，没有就拦截
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+controller：映射）
     * @return true：放行  false：拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            //被拦截
            response.sendRedirect("/web/login.html");
            return false;
        }
        // 否则放行
        return true;
    }
}
