package com.aoi.core.my.mybatis;

import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyMyBatisConfig
 * @Description IMapperScan导入IMapperScannerRegister，为dao装配bean
 * @Author 86184
 * @Date 2024/10/20 13:35
 */
@Configuration
@IMapperScan(basePackage = "com.aoi.core.my.mybatis.dao")
public class MyMyBatisConfig {
}
