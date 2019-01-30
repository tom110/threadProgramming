/*
* synchronized方法执行时，非synchronized方法可以插入执行
* */
package com.tom.thread;

public class Thread006 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"stoped");
    }

    public /*synchronized*/ void m2(){
        System.out.println(Thread.currentThread().getName()+"start...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"stoped");
    }

    public static void main(String[] args){
        Thread006 t=new Thread006();
//        new Thread(()->t.m1(),"t1").start();
//        new Thread(()->t.m2(),"t2").start();

        new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();
    }
}
