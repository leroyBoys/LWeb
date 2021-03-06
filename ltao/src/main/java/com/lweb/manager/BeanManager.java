package com.lweb.manager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * Created by leroy:656515489@qq.com
 * 2018/5/9.
 */
@Component
public class BeanManager implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        BeanManager.context = context;
    }
    public static ApplicationContext getContext(){
        return context;
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> resourceUrlProviderClass) {
        return context.getBean(resourceUrlProviderClass);
    }
}
