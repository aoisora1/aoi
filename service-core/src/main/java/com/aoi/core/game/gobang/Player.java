package com.aoi.core.game.gobang;

public class Player {
    Integer id;
    PlayerEnum playerEnum;

    public Player(Integer id, PlayerEnum playerEnum) {
        this.id = id;
        this.playerEnum = playerEnum;
    }
}
