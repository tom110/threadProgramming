/*
* AtomXX类的方法本身是带有原子性的，执行效率要快很多，是从底层优化的类
* */
package com.tom.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Thread014 {
    AtomicInteger count=new AtomicInteger(0);
    void m(){
        for(int i=0;i<10000;i++)
            count.incrementAndGet();//自带原子性的count++
    }
    public static void main(String[] args){
        Thread014 t=new Thread014();
        List<Thread> threads=new ArrayList<>();
        for(int i=0;i<10;i++){
            threads.add(new Thread(t::m,"thread"+i));
        }
        threads.forEach(o->o.start());

        //等待所有线程结束
        threads.forEach(o->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
