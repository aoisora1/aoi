package com.aoi.core.stream.file;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName AbstractStreamHandler
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/3 14:30
 */
public class DefaultStreamHandler<T extends FileStreamHandler<E>, E> {
    private T handler;
    private LinkedList<LineHandler<E>> handlers;
    // TODO 监控信息

    public void handle(File file, T t) {
        this.handler = t;
        initHandlers(handler);
        handler.setFile(file);
        try {
            while (handler.hasNext()) {
                List<String> next = handler.getNext();
                next.stream().map(handler::convertEntity).forEach(this::handleLine);
            }
        } finally {
            handler.close();
        }
    }

    private void initHandlers(T handler) {
        handlers = new LinkedList<>();
        List<LineHandler<E>> handlers1 = handler.getHandlers();
        for (int i = 0; i < handlers1.size(); i++) {
            handlers.add(handlers1.get(i));
        }
    }

    private void handleLine(E e) {
        // TODO order
        handlers.forEach(handler -> handler.handle(e));
    }
}
