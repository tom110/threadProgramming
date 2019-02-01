/*
* ReentrantLock有lockinterruptibly方法，使得一直等待的线程可以被打断
* 此示例程序需完善
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock3 {

    void m1(ReentrantLock lock){

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" start");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(ReentrantLock lock){
        try {
//            lock.lock();
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+" start");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" can be interrupt");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        ReentrantLock3 t=new ReentrantLock3();
        ReentrantLock lock=new ReentrantLock();
        Thread t1= new Thread(()->t.m1(lock),"t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2= new Thread(()->t.m2(lock),"t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();
    }
}
