package com.aoi.core.game.wuziqi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameManager {
    private final Logger logger = LoggerFactory.getLogger(GameManager.class);

    @Autowired
    private GamePool pool;

    public int newGame(int id0, int id1) {
        int id = pool.newGame(id0, id1).getId();
        logger.info("用户{},{}开始对局{}", id0, id1, id);
        return id;
    }

    public boolean luozi(int gameId, int id, int x, int y) {
        PoolGame poolGame = pool.get(gameId);
        if (poolGame == null || !poolGame.isGaming()) {
            throw new RuntimeException("对局不存在!");
        }
        boolean win = poolGame.luozi(id, x, y);
        if (win) {
            logger.info("对局{}完成", gameId);
            endGame(gameId);
        }
        return win;
    }

    public void endGame(int gameId) {
        logger.info("结束对局{}", gameId);
        PoolGame poolGame = pool.get(gameId);
        if (poolGame == null || !poolGame.isGaming()) {
            throw new RuntimeException("对局不存在!");
        }
        pool.release(gameId);
        poolGame.endGame();
    }
}
