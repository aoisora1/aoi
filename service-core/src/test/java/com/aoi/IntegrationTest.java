package com.aoi;

import com.aoi.config.BeanExcludeFilter;
import com.aoi.config.MockBeanConfig;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@TypeExcludeFilters(value = {BeanExcludeFilter.class}) //排除指定bean
@ActiveProfiles("test")
@Transactional // 开启全局事务，集成测试后回滚数据库
@SpringBootTest(classes = {TestApplicationMain.class, MockBeanConfig.class}) //全局mock bean
public @interface IntegrationTest {
}
