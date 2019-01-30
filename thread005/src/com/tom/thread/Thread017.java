/*
* synchronized锁的载体是一个对象，对象变化锁丢失
* 注意：不要用字符串常量做锁的对象，因为字符串一样的为同一对象，a="hello"和b="hello",a和b是同一对象
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;

public class Thread017 {
    Object o=new Object();

    void m(){
        synchronized (o){
            while(true){
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Thread017 t=new Thread017();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m,"t2").start();//此处开一个线程因为有锁，此线程会处于等待

        t.o=new Object();//对象变化，锁失去，注意此处锁失去是指第一个和第二个都没有锁了

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m,"t3").start();
        t.o=new Object();
    }
}
