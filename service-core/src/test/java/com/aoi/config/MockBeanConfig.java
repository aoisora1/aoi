package com.aoi.config;

import com.aoi.core.service.ExternalService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName MockBeanConfig
 * @Description 集成测试时，全局指定mockbean，一般获取外部数据的bean需要mock
 * @Author 86184
 * @Date 2025/7/8 09:10
 */
@TestConfiguration
public class MockBeanConfig {

    @Bean
    public ExternalService otherService() {
        ExternalService mock = Mockito.mock(ExternalService.class);
        Mockito.when(mock.get()).thenReturn("external");
        return mock;
    }
}
