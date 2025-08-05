package com.aoi.core.game.gobang.context;

import com.aoi.core.game.GameEnum;
import com.aoi.core.game.context.StartContext;

public class GobangStartContext extends StartContext {
    private int id0;
    private int id1;

    public GobangStartContext(int id, int id0, int id1) {
        super(GameEnum.gobang.getCode(), id);
        this.id0 = id0;
        this.id1 = id1;
    }

    public int getId0() {
        return id0;
    }

    public int getId1() {
        return id1;
    }

    @Override
    public String getStartInfo() {
        return super.getStartInfo().concat(String.format(",玩家1:%d,玩家2:%d", getId0(), getId1()));
    }
}
