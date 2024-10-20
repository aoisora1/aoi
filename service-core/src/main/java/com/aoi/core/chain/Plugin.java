package com.aoi.core.chain;

import org.springframework.core.Ordered;

/**
 * @ClassName Plugin
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/20 17:21
 */
public interface Plugin extends Ordered {
    void handle(ChainContext context);
}
