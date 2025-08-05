package com.aoi.core.game.gobang.context;

import com.aoi.core.game.context.StepContext;

public class GobangStepContext extends StepContext {
    private int pId;
    private int x;
    private int y;

    public GobangStepContext(int pId, int x, int y) {
        this.pId = pId;
        this.x = x;
        this.y = y;
    }

    public Integer getpId() {
        return pId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String getStepInfo() {
        return String.format("玩家%d下棋%d,%d", pId, x, y);
    }
}
