package com.aoi.core.stream.file;

import java.io.*;
import java.util.List;

public interface FileStreamHandler<T> {

    void setFile(File file);

    boolean hasNext();

    List<String> getNext();

    T convertEntity(String line);

    void addHandler(LineHandler<T> handler);

    List<LineHandler<T>> getHandlers();

    void close();
}
