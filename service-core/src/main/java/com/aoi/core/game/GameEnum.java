package com.aoi.core.game;

public enum GameEnum {
    gobang(0, "五子棋", "gobang");

    private int code;
    private String name;
    private String en;

    GameEnum(int code, String name, String en) {
        this.code = code;
        this.name = name;
        this.en = en;
    }

    public static GameEnum getGameEnum(int code) {
        for (GameEnum gameEnum : GameEnum.values()) {
            if (gameEnum.getCode() == code) {
                return gameEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEn() {
        return en;
    }
}
