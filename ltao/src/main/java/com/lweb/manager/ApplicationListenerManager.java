package com.lweb.manager;

import com.dbbase.enums.PlatformType;
import com.lgame.util.file.PropertiesTool;
import com.lgame.util.load.properties.PropertiesHelper;
import com.lqsmart.core.LQStart;
import com.lweb.cache.LQCache;
import com.lweb.cache.LQCacheKey;
import com.lweb.moudle.AppConfig;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by leroy:656515489@qq.com
 * 2018/5/7.
 */
@Component
public class ApplicationListenerManager implements ApplicationListener {
    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
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

        try {
            LQStart.scan(env.getProperty("lq.scan"));
            Properties p = new Properties();
            for(String str:env.getActiveProfiles()){
                p.putAll(PropertiesTool.loadProperty("config/application-"+str+".properties"));
            }

            LQStart.init(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        URLManager.instance().init((AppConfig) BeanManager.getBean("appConfig"));

        LQCache.getIntance().getCache(LQCacheKey.DefaultMainID, PlatformType.weixin);
    }

    protected void stop(){
        System.out.println("==================应用关闭");
    }
}