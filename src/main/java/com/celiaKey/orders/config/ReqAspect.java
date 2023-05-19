package com.celiaKey.orders.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面 -- 打印请求参数
 */
@Slf4j
@Aspect
@Component
public class ReqAspect {
    /**
     * 指定切入点表达式
     * public * com.hkl.modules.*.controller..*(..))
     */
    @Pointcut("execution( * com.celiaKey.orders.mvc.controller.BannerController.*(..)) || " +
            "execution( * com.celiaKey.orders.mvc.controller.LoginController.*(..))")
    public void getMethods() {
    }


    @Before(value = "getMethods()")
    public void before(JoinPoint joinPoint) {
        //执行目标方法之前执行的操作
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求 url
        String url = request.getRequestURL().toString();
        log.info("ReqAspect URL is {}", url);

        // 打印 Http method
        String method = request.getMethod();
        log.info("ReqAspect HTTP method is {}", method);

        // 打印 Http method
        log.info("ReqAspect Class --- {}, {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        // 打印请求的 IP
        log.info("ReqAspect IP: {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("ReqAspect Args : {}", JSON.toJSON(joinPoint.getArgs()));
    }

    @After(value = "getMethods()")
    public void after() {
        //执行目标方法之后执行的操作
        System.out.println("LogAspect.after>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
