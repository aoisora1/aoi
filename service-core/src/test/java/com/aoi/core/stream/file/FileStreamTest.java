package com.aoi.core.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @ClassName FileStreamTest
 * @Description FileStreamTest
 * @Author 86184
 * @Date 2024/10/3 15:09
 */
public class FileStreamTest {

    @org.junit.jupiter.api.Test
    public void test() {
        DefaultStreamHandler<CsvFileStreamHandler<Test>, Test> handler = new DefaultStreamHandler<>();
        CsvFileStreamHandler<Test> csvFileStreamHandler = new CsvFileStreamHandler<>(Test.class);

        LineHandler<Test> h1 = t -> t.name = t.name.toUpperCase();
        LineHandler<Test> h2 = System.out::println;
        csvFileStreamHandler.addHandler(h1);
        csvFileStreamHandler.addHandler(h2);

        handler.handle(new File("C:\\Users\\86184\\Desktop\\test.csv"), csvFileStreamHandler);
    }

    static class Test {
        String name;

        @Override
        public String toString() {
            return "Test{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
