/*
* ReentrantLock有trylock方法，可以尝试获得锁，也就是要锁的超时机制
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
    ReentrantLock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //trylock的两种使用方式，第二种更常用
    void m2() {
        /*
        boolean locked=lock.tryLock();
        if(locked) {
            System.out.println("m2....");
            lock.unlock();
        }
        */
        boolean locked = false;
        try {
            locked = lock.tryLock(12, TimeUnit.SECONDS);
            System.out.println("m2  "+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock2 t = new ReentrantLock2();
        new Thread(t::m1, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2, "t2").start();
    }
}
