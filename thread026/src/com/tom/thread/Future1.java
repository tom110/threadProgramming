package com.tom.thread;

import java.util.concurrent.*;

public class Future1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());//此处会阻塞等待线程返回结果


        //*************************************************

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> future = service.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });
        System.out.println(future.isDone());//是否执行完毕
        System.out.println(future.get());
        System.out.println(future.isDone());//是否执行完毕
    }
}
