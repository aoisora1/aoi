package com.aoi.core.my.mybatis;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @ClassName IMapperScannerRegister
 * @Description 用于注入IMapperScannerConfig bean
 * @Author 86184
 * @Date 2024/10/20 12:55
 */
public class IMapperScannerRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        // IMapperScannerRegister由IMapperScan导入，获取导入时的配置信息
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(IMapperScan.class.getName()));

        // 创建IMapperScannerConfig的BeanDefinition
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(IMapperScannerConfig.class);
        // 将basePackage属性赋值
        String[] basePackages = mapperScanAttrs.getStringArray("basePackage");
        builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(Arrays.stream(basePackages).collect(Collectors.toList())));
        registry.registerBeanDefinition("IMapperScannerConfig", builder.getBeanDefinition());
    }
}
