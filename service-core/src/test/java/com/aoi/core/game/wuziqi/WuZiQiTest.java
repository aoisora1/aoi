package com.aoi.core.game.wuziqi;

import com.aoi.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class WuZiQiTest {
    private final Logger logger = LoggerFactory.getLogger(WuZiQiTest.class);

    @Autowired
    GameManager game;

    @Autowired
    private GamePool pool;

    @Test
    public void test() {
        System.out.println("xx");
        int gameId = game.newGame(0, 1);
        game.luozi(gameId, 0, 0, 0);
        game.luozi(gameId, 1, 1, 0);
        game.luozi(gameId, 0, 0, 1);
        game.luozi(gameId, 1, 1, 1);
        game.luozi(gameId, 0, 0, 2);
        game.luozi(gameId, 1, 1, 2);
        game.luozi(gameId, 0, 0, 3);
        game.luozi(gameId, 1, 1, 3);
        boolean luozi = game.luozi(gameId, 0, 0, 4);
        System.out.println(luozi);
    }

    @Test
    public void test2() throws InterruptedException {
        logger.info("test2");
        int i = game.newGame(0, 1);
        int i2 = game.newGame(0, 1);
        int i3 = game.newGame(0, 1);
        int i4 = game.newGame(0, 1);
        int i5 = game.newGame(0, 1);

        Thread.sleep(10000);
        game.endGame(i);
        game.endGame(i2);
        game.endGame(i3);
        game.endGame(i4);
        game.endGame(i5);
        Thread.sleep(10000);
        System.out.println();
    }
}
