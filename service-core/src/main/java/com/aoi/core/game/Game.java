package com.aoi.core.game;

import com.aoi.core.game.context.EndContext;
import com.aoi.core.game.context.StartContext;
import com.aoi.core.game.context.StepContext;
import com.aoi.core.game.context.StepResultContext;

public abstract class Game {
    private int id;
    private int code;
    private long startTime;
    private long endTime;
    private boolean gaming;

    public Game(StartContext context) {
        this.id = context.getId();
        this.code = context.getCode();
    }

    public void startGame(StartContext context) {
        this.startTime = System.currentTimeMillis();
        this.endTime = -1;
        this.gaming = true;
    }

    public abstract StepResultContext step(StepContext context);

    public GameInfo endGame(EndContext context) {
        this.gaming = false;
        this.endTime = System.currentTimeMillis();
        GameInfo info = generateGameInfo(context);
        doEndGame(context);
        return info;
    }

    protected abstract void doEndGame(EndContext context);

    protected abstract GameInfo generateGameInfo(EndContext context);

    public int getId() {
        return id;
    }

    public boolean isGaming() {
        return gaming;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
