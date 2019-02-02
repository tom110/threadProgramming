/*
* 认识executor
* */
package com.tom.thread;

import java.util.concurrent.Executor;

public class Executor1 implements Executor{

    public static void main(String[] args){
        new Executor1().execute(()->System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
