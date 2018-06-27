package com.lweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class LmanagerApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(LmanagerApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(LmanagerApplication.class, args);
	}
}
