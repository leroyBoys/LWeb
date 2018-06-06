package com.lweb.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 设备拦截器
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
@Configuration
public class DeviceAdapter implements WebMvcConfigurer {
    @Bean   //把我们的拦截器注入为bean
    public URLInterceptor getMyInterceptor(){
        return new URLInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
///多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
    }
}
