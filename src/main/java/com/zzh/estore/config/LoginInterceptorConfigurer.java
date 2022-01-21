package com.zzh.estore.config;

import com.zzh.estore.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zzh
 * @description ：注册处理拦截器
 * @date ：Created in 2022/1/9 13:05
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /**配置拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建自定义的拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        // 配置白名单:存放在一个List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/register");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/product/**");
        // 完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns); // 要拦截的url
    }
}
