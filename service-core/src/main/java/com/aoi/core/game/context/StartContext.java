package com.aoi.core.game.context;

public abstract class StartContext extends GameContext {
    private int code;
    private int id;

    public StartContext(int code, int id) {
        this.code = code;
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public String getStartInfo() {
        return String.format("code:%d,id:%d", getCode(), getId());
    }
}
