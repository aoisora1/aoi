package com.aoi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ActiveProfiles("test")
@Transactional // 开启全局事务，集成测试后回滚数据库
@SpringBootTest(classes = TestApplicationMain.class)
public @interface IntegrationTest {
}
