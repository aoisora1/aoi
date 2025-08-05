package com.aoi.core.game;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(GamePoolProperties.class)
public class GameConfiguration {

    @Bean
    public GamePoolManager gamePoolManager(ApplicationContext applicationContext, GameFactory gameFactory) {
        GamePoolProperties defaultProperties = applicationContext.getEnvironment().getProperty("game.default", GamePoolProperties.class);
        Map<Integer, GamePool> gamePoolMap = new HashMap<>();

        for (GameEnum gameEnum : GameEnum.values()) {
            GamePoolProperties gamePoolProperties = applicationContext.getEnvironment().getProperty("game." + gameEnum.getEn(), GamePoolProperties.class);
            GamePool gamePool;
            if (gamePoolProperties != null) {
                gamePool = new GamePool(gamePoolProperties, gameFactory);
            } else {
                gamePool = new GamePool(defaultProperties, gameFactory);
            }
            gamePoolMap.put(gameEnum.getCode(), gamePool);
        }

        return new GamePoolManager(gamePoolMap);
    }
}
