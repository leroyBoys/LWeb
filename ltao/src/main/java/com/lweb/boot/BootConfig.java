package com.lweb.boot;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
@Configuration
public class BootConfig {
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean(new FilterManager());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
