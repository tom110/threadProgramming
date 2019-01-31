/*
* ReentrantLock相比synchronized锁有如下几个特点
* 1.必须手动解锁，一般在finally里
* 2.异常不会失去锁
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1 {
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
        }finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2..");
        lock.unlock();
    }

    public static void main(String[] args){
        ReentrantLock1 t=new ReentrantLock1();
        new Thread(t::m1,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2,"t2").start();
    }
}
