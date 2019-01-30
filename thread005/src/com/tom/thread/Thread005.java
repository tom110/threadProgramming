/*
* synchronized 锁定的是在堆内的对象
* */
package com.tom.thread;

public class Thread005 implements Runnable{
    private int count=10;

    @Override
    public  synchronized  void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+" | "+ count);
    }

    public static void main(String[] args){
        Thread005 thread005=new Thread005();
        for(int i=0;i<5;i++){
            new Thread(thread005,"HEAD "+i).start();
        }
    }
}
