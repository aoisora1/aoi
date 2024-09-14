package com.aoi.assembly.operation;

import java.lang.annotation.*;

/**
 * @Description 审计日志
 * @Author 86184
 * @Date 2024/9/14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperationLog {
    String log() default "";
}
