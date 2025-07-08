package com.aoi.core.demo.current;

/**
 * @ClassName VolatileTest
 * @Description 验证volatile可见性
 * @Author 86184
 * @Date 2025/6/3 11:45
 */
public class VolatileTest {
    public static volatile boolean flag = false;
//    public static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("end");
                    break;
                }
            }
        }).start();
        Thread.sleep(1000);
        flag = true;
    }
}
