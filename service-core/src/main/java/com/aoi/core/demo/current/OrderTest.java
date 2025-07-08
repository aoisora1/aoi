package com.aoi.core.demo.current;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName OrderTest
 * @Description 验证指令重排
 * @Author 86184
 * @Date 2025/6/3 12:27
 */
public class OrderTest {
    private static int a,b,x,y;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            count++;
            a = 0;
            b = 0;
            x = 1;
            y = 0;
            CountDownLatch c = new CountDownLatch(1);
//            Thread t1 = new Thread(() -> {
//                a = 1;
//                x = a;
//                c.countDown();
//            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = b;
                c.countDown();
            });
            //t1.start();
            t2.start();
            c.await();
            if (x == 0 || y == 0) {
                System.out.println(count);
                break;
            }
        }
    }
}
