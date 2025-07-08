package com.aoi.core.service;

import com.aoi.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName IntegerationMockTest
 * @Description 测试MockBeanConfig生效
 * @Author 86184
 * @Date 2025/7/8 09:15
 */
//TODO 同一个上下中，每个测试方法执行完需要清理mock
@IntegrationTest
public class IntegrationMockTest {
    @Autowired
    ExternalService externalService;

    @Test
    public void testExternalService1() {
        Assertions.assertThat(externalService.get()).isNull();
    }

    @Test
    public void testExternalService2() {
        Mockito.when(externalService.get()).thenReturn("external");
        Assertions.assertThat(externalService.get()).isEqualTo("external");
    }

    @Test
    public void testExternalService3() {
        Assertions.assertThat(externalService.get()).isEqualTo("external");
    }
}
