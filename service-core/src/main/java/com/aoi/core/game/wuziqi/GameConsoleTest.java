package com.aoi.core.game.wuziqi;

import java.util.Scanner;

public class GameConsoleTest {

    public static void main(String[] args) {
        int[] players = new int[2];
        players[0] = 0;
        players[1] = 1;

        Scanner sc = new Scanner(System.in);

        boolean win = false;
        Game g = new Game(players[0],  players[1]);
        while (!win) {
            for (int i = 0; i < players.length; i++) {
                System.out.println(String.format("输入下棋位置，逗号分隔。%d号下棋", players[i]));
                String s = sc.nextLine();
                String[] split = s.split(",");
                win = g.luozi(players[i], Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                if (win) {
                    break;
                }
            }
        }
        sc.close();
    }
}
