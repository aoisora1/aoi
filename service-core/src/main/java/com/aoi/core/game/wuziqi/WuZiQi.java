package com.aoi.core.game.wuziqi;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class WuZiQi {
    private Player p0;
    private Player p1;
    private Player winPlayer;
    private Deque<Luozi> deque; // 对局落子信息
    private int[][] qiPan; // 棋盘，一层横，二层纵
    private int xCount = 20; // 棋盘横
    private int yCount = 20; // 棋盘竖
    private int winCount = 5;

    public WuZiQi(Integer id0, Integer id1) {
        reStart(id0, id1);
    }

    public void reStart(Integer id0, Integer id1) {
        this.p0 = new Player(id0, PlayerEnum.P_0);
        this.p1 = new Player(id1, PlayerEnum.P_1);
        init();
    }

    public boolean luozi(Integer id, int x, int y) {
        Luozi luozi = new Luozi(getP(id), x, y);
        checkLuoZi(luozi);
        doLuoZi(luozi);
        boolean win = win(luozi);
        printQiPan();
        return win;
    }

    public void clear() {
        p0 = null;
        p1 = null;
        winPlayer = null;
        deque.clear();
    }

    // 生成对局信息
    public String generate() {
        return null;
    }

    private void printQiPan() {
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                if (qiPan[i][j] == 0) {
                    System.out.print(" |");
                } else {
                    System.out.print(PlayerEnum.convertColor(qiPan[i][j]) + "|");
                }
            }
            System.out.println();
        }
    }

    private void doLuoZi(Luozi luozi) {
        deque.addLast(luozi);
        qiPan[luozi.x][luozi.y] = luozi.p.playerEnum.getV();
    }

    private void checkLuoZi(Luozi luozi) {
        if (qiPan[luozi.x][luozi.y] != 0) {
            throw new RuntimeException("落子无效");
        }
        if (!deque.isEmpty() && deque.getLast().p == luozi.p) {
            throw new RuntimeException("落子无效");
        }
        if (winPlayer != null) {
            throw new RuntimeException("对局已结束");
        }
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < xCount && y >= 0 && y < yCount;
    }

    private Player getP(Integer id) {
        if (p0.id.compareTo(id) == 0) {
            return p0;
        }
        if (p1.id.compareTo(id) == 0) {
            return p1;
        }
        throw new RuntimeException("对局异常");
    }

    private void init() {
        deque = new ArrayDeque<>();
        resetQiPan();
    }

    private int[][] initQiPan() {
        int[][] pan = new int[xCount][];
        for (int i = 0; i < xCount; i++) {
            pan[i] = new int[yCount];
        }
        return pan;
    }

    private void resetQiPan() {
        if (qiPan == null) {
            qiPan = initQiPan();
            return;
        }
        for (int i = 0; i < xCount; i++) {
            Arrays.fill(qiPan[i], 0);
        }
    }

    private boolean win(Luozi last) {
        boolean win = checkWin(last);
        if (win) {
            winPlayer = last.p;
            System.out.println(String.format("%d赢得对局", winPlayer.id));
        }
        return win;
    }

    private boolean checkWin(Luozi last) {
        int count = 0;
        for (FangXiang f : FangXiang.half) {
            count = 1;
            count += count(last.p.playerEnum.getV(), last.x, last.y, f);
            count += count(last.p.playerEnum.getV(), last.x, last.y, FangXiang.huBu(f));
            if (count >= winCount) {
                return true;
            }
        }
        return false;
    }

    // 判断棋子某个方向上与其相连的同色棋子
    private int count(int v, int x, int y, FangXiang f) {
        Pair<Integer, Integer> next = FangXiang.next(x, y, f);
        int nx = next.getLeft();
        int ny = next.getRight();
        if (!valid(nx, ny)) {
            return 0;
        }
        if (qiPan[nx][ny] == v) {
            return count(v, nx, ny, f) + 1;
        } else {
            return 0;
        }
    }

    // TODO
    // 生成对局信息，用于回溯对局，设计算法，压缩下棋步骤，步骤回溯
    // 悔棋功能
    // 方法优化，命名优化，逻辑整理，抽象
    // 优化，对局池，最多同时N个对局，对局复用(qiPan的内存可以复用)，对局结束后的清理
    // 棋盘满的情况

    class Luozi {
        Player p;
        int x;
        int y;

        public Luozi(Player p, int x, int y) {
            this.p = p;
            this.x = x;
            this.y = y;
        }
    }

    enum FangXiang {
        F_0(0), // 右
        F_1(1), // 右上
        F_2(2), // 上
        F_3(3), // 左上
        F_4(4), // 左
        F_5(5), // 左下
        F_6(6), // 下
        F_7(7), // 右下
        ;

        int v;

        FangXiang(int v) {
            this.v = v;
        }

        static List<FangXiang> half = Lists.newArrayList(F_0,  F_1, F_2, F_3);

        public static FangXiang huBu(FangXiang f) {
            switch (f) {
                case F_0:
                    return F_4;
                case F_1:
                    return F_5;
                case F_2:
                    return F_6;
                case F_3:
                    return F_7;
            }
            throw new RuntimeException("对局异常");
        }

        public static Pair<Integer, Integer> next(int x, int y, FangXiang f) {
            switch (f) {
                case F_0:
                    return Pair.of(x + 1, y);
                case F_1:
                    return Pair.of(x + 1, y + 1);
                case F_2:
                    return Pair.of(x, y + 1);
                case F_3:
                    return Pair.of(x - 1, y + 1);
                case F_4:
                    return Pair.of(x - 1, y);
                case F_5:
                    return Pair.of(x - 1, y - 1);
                case F_6:
                    return Pair.of(x, y - 1);
                case F_7:
                    return Pair.of(x + 1, y - 1);
            }
            throw new RuntimeException("对局异常");
        }
    }
}
