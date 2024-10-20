package com.aoi.core.my.mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * @ClassName IMapperScanner
 * @Description 扫描并自定义dao，扫描basePackages下的接口，即dao，注入bean，且注册为IMapperFactoryBean
 * @Author 86184
 * @Date 2024/10/20 13:09
 */
public class IMapperScanner extends ClassPathBeanDefinitionScanner {
    private BeanDefinitionRegistry registry;
    public IMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
        this.registry = registry;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 重写doScan方法，主要为了beanDefinition后置设置属性
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        processBeanDefinitions(beanDefinitions);

        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();

            String beanClassName = definition.getBeanClassName();
            // 通过构造函数形式，将beanClassName即当前dao的类名传入IMapperFactoryBean
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);

            // 设置bean为IMapperFactoryBean，获取bean时会调用IMapperFactoryBean.getObject，返回代理类
            definition.setBeanClass(IMapperFactoryBean.class);

            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    public void registerFilters() {

        addIncludeFilter((metadataReader, metadataReaderFactory) -> true);

        // exclude package-info.java
        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }
}
