/*
* volatile只能保证线程间变量的可见性，并不能保证执行的原子性
* synchronized既能保证执行的可见性，又能保证原子性，但是效率很低
* */
package com.tom.thread;

import java.util.ArrayList;
import java.util.List;

public class Thread013 {
    volatile int count=0;
    /*synchronized*/ void m(){
        for(int i=0;i<10000;i++) count++;
    }
    public static void main(String[] args){
        Thread013 t=new Thread013();
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
