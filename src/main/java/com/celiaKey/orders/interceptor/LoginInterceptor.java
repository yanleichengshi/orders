package com.celiaKey.orders.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Slf4j
@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * Controller方法处理之前
     * 若返回false，则中断执行，注意：不会进入afterCompletion
     * 重写此方法加入判断语句，可以降低服务器的压力
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*if (StringUtils.isEmpty(request.getHeader("token"))) {
            log.error("missing token");
            throw new MyException(ErrorEnum.MISSING_TOKEN);
        }*/
        String url = request.getRequestURL().toString();
        log.info("LoginInterceptor.preHandle url is {}", url);
        return true;
    }

    /**
     * Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * postHandle虽然post打头，但post、get方法都能处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * DispatcherServlet进行视图的渲染之后
     * 多用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

