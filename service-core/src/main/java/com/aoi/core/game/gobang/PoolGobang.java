package com.aoi.core.game.gobang;

import com.aoi.core.game.context.EndContext;
import com.aoi.core.game.context.StartContext;

public class PoolGobang extends Gobang {

    public PoolGobang(StartContext context) {
        super(context);
    }

    public void clear() {
        deque.clear();
        deque = null;
    }

    @Override
    protected void doEndGame(EndContext context) {
        clear();
    }
}
