package com.aoi.core.game.wuziqi;

import java.util.Objects;

// 对局池
public class PoolGame extends Game {
    private int id;
    private int count; // 已完成对局数
    private long startTime; // 对局开始时间
    private long endTime; // 对局结束时间
    private boolean gaming; // 对局是否正在进行

    public PoolGame(int id, Integer id0, Integer id1) {
        super(id0, id1);
        reStart(id);
    }

    @Override
    public String generate() {
        return super.generate();
    }

    public void clear() {
        this.endTime = System.currentTimeMillis();
        this.gaming = false;
        this.count++;
    }

    public void endGame() {
        this.generate();
        this.clear();
    }

    private void reStart(int id) {
        this.id = id;
        this.gaming = true;
        this.startTime = System.currentTimeMillis();
        this.endTime = 0L;
    }

    public void reStart(int id, Integer id0, Integer id1) {
        super.reStart(id0, id1);
        reStart(id);
    }

    public boolean isGaming() {
        return gaming;
    }

    public int getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PoolGame poolGame = (PoolGame) o;
        return id == poolGame.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
