package com.aoi.core.my.mybatis;

import java.util.List;

/**
 * @ClassName IService
 * @Description 定义service基础方法
 * @Author 86184
 * @Date 2024/10/20 12:49
 */
public interface IService<E> {
    E crete(List<String> param);
    void print(E entity);
}
