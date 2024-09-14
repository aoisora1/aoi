package com.aoi.assembly.operation;

import java.lang.annotation.*;

/**
 * @Description 标记审计日志上报的方法参数
 * @Author 86184
 * @Date 2024/9/14
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperationParam {
    String value() default ""; // 没有值时默认用参数名
}
