/*
* 经典的生产者消费者问题，此处用wait和notifyall来调度线程
* 此处有一个缺点，就是唤醒会全部唤醒，而不是精确唤醒，这样有资源消耗
* */
package com.tom.thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
    final private LinkedList<T> lists=new LinkedList<T>();
    final private int MAX=10;
    private int count=10;

    public synchronized void put(T t){
        while(lists.size()==MAX){//此处用while不用if，是因为while是头尾判断，而if只在头判断，wait()百分之九十九和while用
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        System.out.println(count);
        this.notifyAll();
    }

    public synchronized T get(){
        T t=null;
        while(lists.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=lists.removeFirst();
        count--;
        System.out.println(count);
        this.notifyAll();
        return t;
    }

    public static void main(String[] args){
        MyContainer1<String> c=new MyContainer1<>();
        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<5;j++) System.out.println(c.get());
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0;i<2;i++){
            new Thread(()->{
                for (int j=0;j<25;j++) c.put(Thread.currentThread().getName()+" "+j);
            }).start();
        }
    }
}
