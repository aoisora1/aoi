package com.aoi.core.my.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(IMapperScannerRegister.class)
public @interface IMapperScan {
    String[] basePackage() default {};
}
