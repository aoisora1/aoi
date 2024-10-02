package com.aoi.core.service;

import com.aoi.assembly.exception.BusinessException;
import com.aoi.core.db.dao.TestTableDao;
import com.aoi.core.db.entity.TestTableEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestTableService
 * @Description TestTableService
 * @Author 86184
 * @Date 2024/10/1 16:28
 */
@Service
public class TestTableService extends ServiceImpl<TestTableDao, TestTableEntity> {

    public TestTableEntity queryById(String id) {
        return lambdaQuery()
                .eq(TestTableEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new BusinessException("1001", id));
    }
}
