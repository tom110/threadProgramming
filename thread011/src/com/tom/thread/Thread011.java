/*
* synchronized锁在抛出异常时会失去，所以一定要妥善处理可能的异常
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;

public class Thread011 {
    private int count=1;
    synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"start");
        while(true) {
            count++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " , " + count);

            if (count == 5) {
                int i = 1 / 0;//此处抛出异常，失去锁
            }
        }
    }

    public static void main(String[] args){
        Thread011 t=new Thread011();
        new Thread(t::m1,"t1").start();
        new Thread(t::m1,"t2").start();//如果t1线程不失去锁，t2线程不可能执行
    }

}
