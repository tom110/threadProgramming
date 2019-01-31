/*
* 监控问题的最佳解决方案为门栓方案，门栓await后交出锁，门栓减为0时线程继续进行
* */
package com.tom.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer4 {
    private List<Object> container = new ArrayList<>();


    void add() {
        container.add(new Object());
    }

    int size() {
        return container.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2开始");

            if (c.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("达到5个");
            System.out.println("t2结束");
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add();
                System.out.println("add" + i);

                if (c.size() == 5) {
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1结束");
        }, "t1").start();


    }
}
