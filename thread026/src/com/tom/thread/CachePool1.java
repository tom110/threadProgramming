/*
* CachePool线程池，来几个任务如果没有空闲线程就开一个线程接收任务
* */
package com.tom.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachePool1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newCachedThreadPool();
        System.out.println(service);
        for(int i=0;i<2;i++){
            service.execute(()->{

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.SECONDS.sleep(3);

        System.out.println(service);
    }
}
