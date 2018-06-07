package com.lweb.manager;

import com.lweb.moudle.AppConfig;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by leroy:656515489@qq.com
 * 2018/5/7.
 */
@Component
public class ApplicationListenerManager implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass().getName());
        // 在这里可以监听到Spring Boot的生命周期
        /*if (event instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量
            System.out.println("==================初始化环境变量");
        } else if (event instanceof ApplicationPreparedEvent) { // 初始化完成
            System.out.println("================== 初始化完成");
        } else if (event instanceof ContextRefreshedEvent) { // 应用刷新
          //  System.out.println("==================应用刷新");
        } else */if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            start();
        } else/* if (event instanceof ContextStartedEvent) { //应用启动，需要在代码动态添加监听器才可捕获
            System.out.println("==================应用启动，需要在代码动态添加监听器才可捕获");
        } else */if (event instanceof ContextStoppedEvent) { // 应用停止
            System.out.println("==================应用停止");
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            stop();
        } else {
        }
    }

    protected void start(){
        System.out.println("==================应用已启动完成");

        URLManager.instance().init((AppConfig) BeanManager.getBean("appConfig"));
    }

    protected void stop(){
        System.out.println("==================应用关闭");
    }
}