package com.aoi.core.my.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @ClassName IMapperScanner
 * @Description 使用IMapperScanner扫描dao
 * @Author 86184
 * @Date 2024/10/20 12:58
 */
public class IMapperScannerConfig implements BeanDefinitionRegistryPostProcessor {
    private String basePackage;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        IMapperScanner iMapperScanner = new IMapperScanner(beanDefinitionRegistry);
        iMapperScanner.registerFilters();
        // 开始扫描basePackage下的类，即dao
        iMapperScanner.scan(basePackage);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }


}
