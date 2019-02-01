/*
* ReentrantLock可以是公平锁
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock4 extends Thread {
    private static ReentrantLock lock = new ReentrantLock(/*true*/);//true表示为公平锁

    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "得到锁");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock4 t = new ReentrantLock4();
        Thread t1 = new Thread(t,"t1");
        Thread t2 = new Thread(t,"t2");
        t1.start();
        t2.start();
    }
}
