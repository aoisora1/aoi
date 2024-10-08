package com.aoi.core.service;

import com.aoi.assembly.exception.BusinessException;
import com.aoi.core.db.dao.TestTableDao;
import com.aoi.core.db.entity.TestTableEntity;
import com.aoi.core.dto.TestTableSearchDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
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

    // 测试流式查询用
    public void printAllName() {
        getBaseMapper().executeForAll(new ResultHandler<TestTableEntity>() {
            @Override
            public void handleResult(ResultContext<? extends TestTableEntity> resultContext) {
                TestTableEntity resultObject = resultContext.getResultObject();
                System.out.println(resultObject.getName());
            }
        });
    }

    public Page<TestTableEntity> fuzzyQueryPageByName(TestTableSearchDto query) {
        LambdaQueryWrapper<TestTableEntity> like = new LambdaQueryWrapper<>();
        like.like(TestTableEntity::getName, query.getName());

        Page<TestTableEntity> res = new Page<>();
        res.setPages(query.getPage());
        res.setSize(query.getSize());
        res.setOrders(Lists.newArrayList(new OrderItem("name", true)));
        return getBaseMapper().selectPage(res, like);
    }
}
