package com.celiaKey.orders.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/qwer") 添加拦截路径
        // excludePathPatterns("/asdf") 添加不拦截路径
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/*");
    }
}
