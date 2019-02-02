/*
* volatile和ThreadLocal正好是相反的，volatile为了线程间可见，ThreadLocal为了线程间有自己的变量
* */
package com.tom.thread;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
//    volatile static Person person=new Person();
    static ThreadLocal<Person> person=new ThreadLocal<>();

    public static void main(String[] args){
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.set(new Person());//对应threadLocal

        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(person.name);//对应volatile
            System.out.println(person.get());//对应threadLocal
        }).start();
    }


    static class Person{
        String name="zhansan";
    }
}
