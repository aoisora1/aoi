package com.aoi.core.service;

import com.aoi.IntegrationTest;
import com.aoi.assembly.exception.BusinessException;
import com.aoi.core.db.entity.TestTableEntity;
import com.aoi.core.dto.TestTableSearchDto;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName TestTableTest
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/1 17:52
 */
@IntegrationTest
public class TestTableServiceTest {
    @Autowired
    TestTableService testTableService;

    @Test
    public void testQueryDescriptionByIdThrow() {
        Assertions.assertThatThrownBy(() -> testTableService.queryById("test"))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("code", "1001");
    }

    @Test
    public void testQueryDescriptionById() {
        TestTableEntity testTableEntity = new TestTableEntity();
        testTableEntity.setName("test");
        testTableEntity.setDescription("test");
        testTableService.save(testTableEntity);

        // 测试mybatisplusconifg自动生成属性生效
        TestTableEntity query = testTableService.queryById(testTableEntity.getId());
        Assertions.assertThat(query.getName()).isEqualTo("test");
        Assertions.assertThat(query.getCreateTime()).isNotNull();
        Assertions.assertThat(query.getCreateUser()).isNotNull();
        Assertions.assertThat(query.getUpdateTime()).isNull();
        Assertions.assertThat(query.getUpdateUser()).isNull();

        LambdaUpdateWrapper<TestTableEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(TestTableEntity::getId, testTableEntity.getId());
        testTableEntity.setName("test2");
        testTableService.update(testTableEntity, wrapper);

        query = testTableService.queryById(testTableEntity.getId());
        Assertions.assertThat(query.getName()).isEqualTo("test2");
        Assertions.assertThat(query.getUpdateTime()).isNotNull();
        Assertions.assertThat(query.getUpdateUser()).isNotNull();
    }

    @Test
    public void testStreamQuery() {
        TestTableEntity testTableEntity = new TestTableEntity();
        testTableEntity.setName("test1");
        testTableService.save(testTableEntity);

        testTableEntity.setId(null);
        testTableEntity.setName("test2");
        testTableService.save(testTableEntity);

        testTableEntity.setId(null);
        testTableEntity.setName("test3");
        testTableService.save(testTableEntity);

        testTableEntity.setId(null);
        testTableEntity.setName("test4");
        testTableService.save(testTableEntity);

        testTableService.printAllName();
    }

    @Test
    public void testPQuery() {
        TestTableEntity testTableEntity = new TestTableEntity();
        testTableEntity.setName("test1");
        testTableService.save(testTableEntity);

        testTableEntity.setId(null);
        testTableEntity.setName("test2");
        testTableService.save(testTableEntity);

        testTableEntity.setId(null);
        testTableEntity.setName("test3");
        testTableService.save(testTableEntity);

        testTableEntity.setId(null);
        testTableEntity.setName("test4");
        testTableService.save(testTableEntity);

        TestTableSearchDto testTableSearchDto = new TestTableSearchDto();
        testTableSearchDto.setName("test");
        testTableSearchDto.setPage(1);
        testTableSearchDto.setSize(2);
        Page<TestTableEntity> testTableEntityPage = testTableService.fuzzyQueryPageByName(testTableSearchDto);
        Assertions.assertThat(testTableEntityPage.getRecords()).hasSize(2);
        Assertions.assertThat(testTableEntityPage.getRecords()).extracting(TestTableEntity::getName).contains("test1", "test2");
    }

    @Test
    public void test() {
        TestTableEntity testTableEntity = new TestTableEntity();
        testTableEntity.setName("test1");
        testTableService.save(testTableEntity);

        TestTableEntity testTableEntity1 = testTableService.getBaseMapper().selectById(testTableEntity.getId());
        System.out.println(testTableEntity1.getName());
    }
}
