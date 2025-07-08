package com.aoi.core.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName OtherService
 * @Description 模拟获取外部数据
 * @Author 86184
 * @Date 2025/7/8 09:10
 */
@Service
public class ExternalService {

    public String get() {
        return "local";
    }
}
