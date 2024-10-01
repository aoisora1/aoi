package com.aoi.service;

import com.aoi.IntegrationTest;
import com.aoi.core.service.TestTableService;
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
    public void testQueryDescriptionById() {
        System.out.println(testTableService.queryDescriptionById("test"));
    }
}
