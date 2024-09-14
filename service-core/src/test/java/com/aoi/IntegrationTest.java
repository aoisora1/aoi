package com.aoi;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootTest(classes = TestApplicationMain.class)
public @interface IntegrationTest {
}
