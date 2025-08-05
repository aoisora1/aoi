package com.aoi.core.game;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "game")
public class GameProperties {
    private final Map<String, GamePoolProperties> configs = new HashMap<>();

    public Map<String, GamePoolProperties> getConfigs() {
        return configs;
    }

    public GamePoolProperties getConfig(String code) {
        return configs.get(code);
    }

    public boolean hasConfig(String code) {
        return configs.containsKey(code);
    }

}
