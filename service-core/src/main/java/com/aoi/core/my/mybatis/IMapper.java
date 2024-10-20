package com.aoi.core.my.mybatis;

import java.util.List;

/**
 * @ClassName IMapper
 * @Description 实体类操作接口，E为实体类
 * @Author 86184
 * @Date 2024/10/20 12:35
 */
public interface IMapper<E> {
    E crete(List<String> param);
    void print(E entity);
}
