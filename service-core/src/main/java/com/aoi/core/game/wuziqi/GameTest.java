package com.aoi.core.game.wuziqi;

public class GameTest {

    public static void main(String[] args) {
        Game g = new Game(1, 2);
        g.luozi(1, 0, 6);
        g.luozi(2, 10, 11);
        g.luozi(1, 0, 1);
        g.luozi(2, 10, 12);
        g.luozi(1, 0, 2);
        g.luozi(2, 10, 13);
        g.luozi(1, 0, 3);
        g.luozi(2, 10, 14);
        g.luozi(1, 0, 4);
        g.luozi(2, 10, 15);
    }
}
