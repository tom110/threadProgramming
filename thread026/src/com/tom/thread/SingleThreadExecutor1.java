/*
* 线程池只有一个线程处理任务，可运用于保证任务的顺序执行
* */
package com.tom.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor1 {
    public static void main(String[] args){
        ExecutorService service= Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            final int j=i;
            service.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }
    }
}
