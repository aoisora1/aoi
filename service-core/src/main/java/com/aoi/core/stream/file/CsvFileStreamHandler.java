package com.aoi.core.stream.file;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CsvFileStreamHandler
 * @Description TODO
 * @Author 86184
 * @Date 2024/10/3 14:47
 */
public class CsvFileStreamHandler<T> implements FileStreamHandler<T> {
    private BufferedReader bufferedReader;
    private final int batchSize = 10; // TODO 配置化
    private boolean hasNext = true;
    private Class<T> clazz;
    private final List<LineHandler<T>> handlers = new ArrayList<>();

    private CsvFileStreamHandler() {}

    public CsvFileStreamHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setFile(File file) {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public List<String> getNext() {
        if (!hasNext()) {
            return Collections.emptyList();
        }
        List<String> lines = new ArrayList<>();
        String line;
        try {
            for (int i = 0; i < batchSize; i++) {
                line = bufferedReader.readLine();
                if (line != null) {
                    lines.add(line);
                } else {
                    hasNext = false;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    @Override
    public T convertEntity(String line) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        String[] split = line.split("\\|");
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            try {
                declaredField.set(t, split[i]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    @Override
    public void addHandler(LineHandler<T> handler) {
        handlers.add(handler);
    }

    @Override
    public List<LineHandler<T>> getHandlers() {
        return handlers;
    }

    @Override
    public void close() {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
