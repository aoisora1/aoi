package com.aoi.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName SpringContext
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/8 19:01
 */
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    private static void setContext(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
