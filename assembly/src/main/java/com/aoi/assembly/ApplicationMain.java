package com.aoi.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ApplicationMain
 * @Description 程序主启动类
 * @Author 86184
 * @Date 2024/9/7 21:34
 */
@SpringBootApplication(scanBasePackages = "com.aoi")
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
