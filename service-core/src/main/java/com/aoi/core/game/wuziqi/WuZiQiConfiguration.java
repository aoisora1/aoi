package com.aoi.core.game.wuziqi;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WuZiQiProperties.class)
public class WuZiQiConfiguration {

    @Bean
    public GamePool gamePool(WuZiQiProperties properties) {
        return new GamePool(properties);
    }
}
