package com.aoi.core.my.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName IMapperProxy
 * @Description dao的代理类，所有注入的dao bean都是一个IMapperProxy，由他去完成所有方法的实现
 * @Author 86184
 * @Date 2024/10/20 13:23
 */
public class IMapperProxy<T> implements InvocationHandler {
    private Class<T> mapperInterface;

    public IMapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            // 如果是基础方法，不用代理
            return method.invoke(this, args);
        }
        // TODO 实际实现
        System.out.println(method.getName());
        System.out.println(args);
        return null;
    }
}
