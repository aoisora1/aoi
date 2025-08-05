package com.aoi.core.game.gobang;

import com.aoi.core.game.context.EndContext;

import java.util.Arrays;

public class PoolGobang extends Gobang {

    public void clear() {
        for (int i = 0; i < chessboard.length; i++) {
            Arrays.fill(chessboard[i], 0);
        }
        deque.clear();
    }

    @Override
    protected void doEndGame(EndContext context) {
        clear();
    }
}
