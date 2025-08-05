package com.aoi.core.game;

import com.aoi.core.game.context.StartContext;
import com.aoi.core.game.wuziqi.PoolGobang;
import org.springframework.stereotype.Service;

@Service
public class GameFactory {

    public Game newGame(StartContext context) {
        GameEnum gameEnum = GameEnum.getGameEnum(context.getCode());
        if (gameEnum == null) {
            throw new RuntimeException("非法数据");
        }
        switch (gameEnum) {
            case gobang:
                return new PoolGobang(context);
            default:
                throw new RuntimeException("非法数据");
        }
    }
}
