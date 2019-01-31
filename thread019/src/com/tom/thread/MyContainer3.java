/*
* 此程序为Container2的解决方案，通过反复的wait notify来线程之间切换
* */

package com.tom.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer3 {
    private List<Object> container=new ArrayList<>();



    void add(){
        container.add(new Object());
    }

    int size(){
        return container.size();
    }

    public static void main(String[] args){
        MyContainer1 c=new MyContainer1();
        final Object lock=new Object();

        new Thread(()->{
            System.out.println("t2开始");
            synchronized (lock){
                if(c.size()!=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("达到5个");
                System.out.println("t2结束");
                lock.notify();
            }

        },"t2").start();

        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add();
                    System.out.println("add" + i);

                    if (c.size() == 5) {
                        lock.notify();

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1结束");
        },"t1").start();


    }
}
