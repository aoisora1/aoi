package com.aoi.core.service;

import com.aoi.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName IntegerationMockTest
 * @Description 测试MockBeanConfig生效
 * @Author 86184
 * @Date 2025/7/8 09:15
 */
@IntegrationTest
public class IntegrationMockTest {
    @Autowired
    ExternalService externalService;

    @Test
    public void testExternalService() {
        Assertions.assertThat(externalService.get()).isEqualTo("external");
    }
}
