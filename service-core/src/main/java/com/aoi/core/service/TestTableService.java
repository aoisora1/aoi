package com.aoi.core.service;

import com.aoi.core.db.dao.TestTableDao;
import com.aoi.core.db.entity.TestTableEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestTableService
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/1 16:28
 */
@Service
public class TestTableService extends ServiceImpl<TestTableDao, TestTableEntity> {

    public String queryDescriptionById(String id) {
        TestTableEntity testTableEntity = lambdaQuery()
                .select(TestTableEntity::getDescription)
                .eq(TestTableEntity::getName, id)
                .oneOpt()
                .orElseThrow(() -> new RuntimeException("1001"));// TODO
        return testTableEntity.getDescription();
    }
}
