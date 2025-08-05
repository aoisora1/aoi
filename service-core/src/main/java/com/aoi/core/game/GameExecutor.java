package com.aoi.core.game;

import com.aoi.core.game.context.EndContext;
import com.aoi.core.game.context.StartContext;
import com.aoi.core.game.context.StepContext;
import com.aoi.core.game.context.StepResultContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameExecutor {
    private final Logger logger = LoggerFactory.getLogger(GameExecutor.class);

    @Autowired
    private GamePoolManager manager;

    public int newGame(StartContext context) {
        logger.info("新建游戏，对局信息: {}", context.getStartInfo());
        return manager.getPool(context.getCode()).newGame(context).getId();
    }

    public StepResultContext step(int code, int id, StepContext context) {
        logger.info("对局{}进行操作{}", context.getStepInfo());
        Game game = (Game) manager.getPool(code).get(id);
        StepResultContext result = game.step(context);
        if (StringUtils.isNotEmpty(result.getResultInfo())) {
            logger.info("{}", result.getResultInfo());
        }
        if (result.isFinish()) {
            endGame(code, id, new EndContext());
        }
        return result;
    }

    public void endGame(int code, int id, EndContext context) {
        Game game = (Game) manager.getPool(code).get(id);
        GameInfo gameInfo = game.endGame(context);
        // TODO 保存对局信息
    }
}
