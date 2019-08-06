package com.xiaowei.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CountDownLatchDemo
 * @Desciption CountDownLatchDemo
 * @Author weizheng
 * @Date 2019/8/6 16:32
 **/
public class CountDownLatchDemo implements Runnable {

    private static final CountDownLatch latch = new CountDownLatch(10);

    private static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public void run() {
        try {
            int time = new Random().nextInt(10) * 1000;
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName() + " takes " + time + " ms check Complete!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i < 10 ; i ++ ) {
            executor.submit(demo);
        }
        System.out.println("loop end!");

        latch.await();

        System.out.println("Fire!");
        executor.shutdown();
    }
}
