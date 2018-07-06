package com.lweb;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class LmanagerApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(LmanagerApplication.class);
	}

	@Bean//使用@Bean注入fastJsonHttpMessageConvert
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		//1.需要定义一个Convert转换消息的对象
		FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
		//2.添加fastjson的配置信息，比如是否要格式化返回的json数据
//
		FastJsonConfig fastJsonConfig=new FastJsonConfig();
		//fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//3.在convert中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter=fastConverter;
		return new HttpMessageConverters(converter);
	}
	public static void main(String[] args) {
		SpringApplication.run(LmanagerApplication.class, args);
	}
}
