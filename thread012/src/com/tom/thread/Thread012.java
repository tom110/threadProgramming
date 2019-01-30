/*
* volatile关键字使得线程间变量可见可调用，原理是通知cpu处理缓存中的数据去主线程内存中刷新
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;

public class Thread012 {
    volatile boolean running=true;
    void m1(){
        System.out.println(Thread.currentThread().getName()+"start");
        while (running){

        }
        System.out.println(Thread.currentThread().getName()+"stop");
    }

    public static void main(String[] args){
        Thread012 t=new Thread012();
        new Thread(t::m1,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running=false;
    }
}
