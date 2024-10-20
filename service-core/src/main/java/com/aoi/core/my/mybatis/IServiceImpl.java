package com.aoi.core.my.mybatis;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName IServiceImpl
 * @Description IService实现，所有service都应继承该类，由mapper去实际操作实体
 * @Author 86184
 * @Date 2024/10/20 12:50
 */
public class IServiceImpl<M extends IMapper<E>, E> implements IService<E> {
    // 通过自动配置的形式，为每个dao注入了bean，每个service就能获取到bean直接使用
    @Autowired
    private M mapper;

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @Override
    public E crete(List<String> param) {
        return mapper.crete(param);
    }

    @Override
    public void print(E entity) {
        mapper.print(entity);
    }
}
