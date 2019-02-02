/*
* 一种靠内部类实现的线程安全的单例模式
* */
package com.tom.thread;

import java.util.Arrays;

public class Singleton {
    private Singleton() {
        System.out.println("Singleton");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getSingleton(){
        return Inner.s;
    }

    public static void main(String[] args){
        Thread[] threads=new Thread[50];
        for(int i=0;i<50;i++) {
            threads[i]=new Thread(()->{
               Singleton.getSingleton();
            });
        }
        Arrays.asList(threads).forEach(o->o.start());

    }

}
