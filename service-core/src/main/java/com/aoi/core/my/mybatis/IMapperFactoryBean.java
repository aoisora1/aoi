package com.aoi.core.my.mybatis;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @ClassName IMapperFactoryBean
 * @Description mapper的FactoryBean，为dao注入FactoryBean，getObject返回代理类
 * @Author 86184
 * @Date 2024/10/20 13:17
 */
public class IMapperFactoryBean<T> implements FactoryBean<T> {
    // 标识自己是哪个dao
    private Class<T> mapperInterface;

    public IMapperFactoryBean() {
    }

    public IMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        // 获取的dao实际是IMapperProxy这个代理类
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, new IMapperProxy<>(mapperInterface));
    }

    @Override
    public Class<?> getObjectType() {
        // 知道了dao类名，注入时才能找到对应的bean
        return mapperInterface;
    }
}
