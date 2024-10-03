package com.aoi.core.stream.file;

@FunctionalInterface
public interface LineHandler<T> {

    void handle(T t);
}
