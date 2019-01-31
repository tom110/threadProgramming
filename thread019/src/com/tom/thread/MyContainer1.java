/*
* 监控问题，当容器中的个数为5时进行通知
* 此处注意监控元素要用volatile修饰，不然线程间不可见，那么也就没有监控问题
* 此程序存在问题，while（true）太消耗资源
* */
package com.tom.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1 {
    private /*volatile*/ List<Object> container=new ArrayList<>();

    void add(){
        container.add(new Object());
    }

    int size(){
        return container.size();
    }

    public static void main(String[] args){
        MyContainer1 c=new MyContainer1();

        new Thread(()->{
           for(int i=0;i<10;i++){
               c.add();
               System.out.println("add"+i);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"t1").start();

        new Thread(()->{
           while(true){
               if(c.size()==5){
                   System.out.println("达到5个");
                   break;
               }
           }
           System.out.println("t2结束");
        },"t2").start();
    }
}
