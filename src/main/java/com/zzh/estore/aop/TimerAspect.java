package com.zzh.estore.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/18 17:29
 */
@Component // 将当前类的对象创建使用维护交由Spring容器维护
@Aspect  /* 将当前类标记为切面类 */
public class TimerAspect {
    @Around("execution(* com.zzh.estore.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 先记录当前时间
        long start = System.currentTimeMillis();
        // 调用目标方法:login
        Object result = pjp.proceed();
        // 插入数据库
        // TODO
        // 后记录当前时间
        long end = System.currentTimeMillis();
        System.out.println("耗时："+ (end-start));
        return result;
    }

}
