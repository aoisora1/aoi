package com.aoi.core.game.wuziqi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameManager {

    @Autowired
    private GamePool pool;

    public int newGame(int id0, int id1) {
        return pool.newGame(id0, id1).getId();
    }

    public boolean luozi(int gameId, int id, int x, int y) {
        PoolGame poolGame = pool.get(gameId);
        boolean win = poolGame.luozi(id, x, y);
        if (win) {
            pool.release(gameId);
            poolGame.generate(); // 记录对局信息
            poolGame.clear();
        }
        return win;
    }
}
