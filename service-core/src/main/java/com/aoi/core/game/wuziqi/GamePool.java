package com.aoi.core.game.wuziqi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GamePool {
    private final Logger logger = LoggerFactory.getLogger(GamePool.class);
    private Map<Integer, WuZiQiPoolWuZiQi> gaming; // 正在进行的对局
    private List<WuZiQiPoolWuZiQi> coreGame; // 核心对局
    private LinkedList<WuZiQiPoolWuZiQi> gamesFree; // 非核心空闲对局
    private WuZiQiProperties properties;
    private int count; // 对局累加器，用数据库自增主键
    private long expirationTime;

    public GamePool(WuZiQiProperties properties) {
        this.gaming = new HashMap<>();
        this.coreGame = new ArrayList<>();
        this.properties = properties;
        this.gamesFree = new LinkedList<>();
        this.expirationTime = (long) properties.getKeepAliveTime() * 60 * 1000;
        this.startDaemon();
    }

    public WuZiQiPoolWuZiQi newGame(int id0, int id1) {
        if (gaming.size() > properties.getMaxSize()) {
            throw new RuntimeException("对局已满请稍等!");
        }

        // 先使用核心对局，先撑满核心对局数
        if (coreGame.size() < properties.getCoreSize()) {
            WuZiQiPoolWuZiQi wuZiQiPoolGame = new WuZiQiPoolWuZiQi(count++, id0, id1);
            coreGame.add(wuZiQiPoolGame);
            gaming.put(wuZiQiPoolGame.getId(), wuZiQiPoolGame);
            return wuZiQiPoolGame;
        }

        // 核心对局已满，优先使用核心非进行中对局
        for (int i = 0; i < coreGame.size(); i++) {
            if (!coreGame.get(i).isGaming()) {
                coreGame.get(i).reStart(count++, id0, id1);
                gaming.put(coreGame.get(i).getId(), coreGame.get(i));
                return coreGame.get(i);
            }
        }

        // 使用非核心空闲对局
        if (!gamesFree.isEmpty()) {
            WuZiQiPoolWuZiQi first = gamesFree.pop();
            first.reStart(count++, id0, id1);
            gaming.put(first.getId(), first);
            return first;
        }

        // 新建非核心对局
        WuZiQiPoolWuZiQi wuZiQiPoolGame = new WuZiQiPoolWuZiQi(count++, id0, id1);
        gaming.put(wuZiQiPoolGame.getId(), wuZiQiPoolGame);
        return wuZiQiPoolGame;
    }

    public void release(int id) {
        WuZiQiPoolWuZiQi wuZiQiPoolGame = gaming.remove(id);
        if (!coreGame.contains(wuZiQiPoolGame)) {
            gamesFree.addLast(wuZiQiPoolGame);
        }
    }

    public WuZiQiPoolWuZiQi get(int id) {
        return gaming.get(id);
    }

    private void startDaemon() {
        Thread thread = new Thread(() -> {
            while (true) {
                logger.info("开始释放非核心空闲对局");
                logger.info("对局信息：进行中对局{}，非核心空闲对局{}", gaming.size(), gamesFree.size());
                releaseFree();
                logger.info("对局信息：进行中对局{}，非核心空闲对局{}", gaming.size(), gamesFree.size());
                logger.info("结束释放非核心空闲对局");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    // TODO 抽象
    // TODO 加锁
    private void releaseFree() {
        long currentTime = System.currentTimeMillis();
        Iterator<WuZiQiPoolWuZiQi> iterator = gamesFree.iterator();
        while (iterator.hasNext()) {
            WuZiQiPoolWuZiQi next = iterator.next();
            if (currentTime - next.getStartTime() > this.expirationTime) {
                iterator.remove();
            }
        }
    }

}
