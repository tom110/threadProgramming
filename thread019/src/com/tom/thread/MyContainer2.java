/*
* 解决Container1中的while（true）资源消耗问题，wait notify方案
* 此处依然有问题，因为wait释放锁，notify并不释放锁，所以虽然t2中唤醒了t1，
* 但是因为t2仍然有锁，所以还是要t1执行完后才能执行t2
* */
package com.tom.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer2 {
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
            }
            System.out.println("达到5个");
            System.out.println("t2结束");
        },"t2").start();

        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add();
                    System.out.println("add" + i);

                    if (c.size() == 5) {
                        lock.notify();
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
