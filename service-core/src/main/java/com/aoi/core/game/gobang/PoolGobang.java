package com.aoi.core.game.gobang;

import com.aoi.core.game.context.EndContext;

public class PoolGobang extends Gobang {

    public void clear() {
        deque.clear();
        deque = null;
    }

    @Override
    protected void doEndGame(EndContext context) {
        clear();
    }
}
