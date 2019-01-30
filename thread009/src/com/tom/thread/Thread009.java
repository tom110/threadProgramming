/*
* synchronized锁的可重入性
* 1.一个synchronized方法可以调用相同对象的另外的synchronized方法，不会死锁
* 2.子类synchronized方法可以调用其父类的synchronized方法，不会死锁
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;

public class Thread009 {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }
    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    public static void main(String[] args){
        Thread009 t=new Thread009();
        t.m1();
    }
}

