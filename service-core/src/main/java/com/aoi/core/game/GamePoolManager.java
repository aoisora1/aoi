package com.aoi.core.game;

import java.util.Map;

public class GamePoolManager {
    private Map<Integer, GamePool> poolMap;

    public GamePoolManager(Map<Integer, GamePool> poolMap) {
        this.poolMap = poolMap;
    }

    public GamePool getPool(int code) {
        return poolMap.get(code);
    }
}
