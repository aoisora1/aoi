package com.aoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName TestApplicationMain
 * @Description TODO
 * @Author 86184
 * @Date 2024/9/8 21:07
 */
@SpringBootApplication(scanBasePackages = "com.aoi")
public class TestApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(TestApplicationMain.class, args);
    }
}
