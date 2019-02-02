package com.tom.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=0l,end=0l;

        start=System.currentTimeMillis();
        List<Integer> result=getPrime(1,200000);
        end =System.currentTimeMillis();
        System.out.println(end-start);

        final int CPUNUM=4;

        ExecutorService service= Executors.newFixedThreadPool(CPUNUM);

        MyTask myTask1=new MyTask(1,50000);
        MyTask myTask2=new MyTask(50001,100000);
        MyTask myTask3=new MyTask(100001,150000);
        MyTask myTask4=new MyTask(150001,200000);

        Future<List<Integer>> f1=service.submit(myTask1);
        Future<List<Integer>> f2=service.submit(myTask2);
        Future<List<Integer>> f3=service.submit(myTask3);
        Future<List<Integer>> f4=service.submit(myTask4);

        start=System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end=System.currentTimeMillis();
        System.out.println(end-start);

    }

    static class MyTask implements Callable<List<Integer>> {
        int startpo,endpo;
        public MyTask(int startpo,int endpo){
            this.startpo=startpo;
            this.endpo=endpo;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(startpo,endpo);
        }
    }

    static boolean isPrime(int num){
        for(int i=2;i<=num/2;i++){
            if(num % i==0){
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getPrime(int start, int end) {
        List<Integer> results=new ArrayList<>();
        for(int i=start;i<=end;i++){
            if(isPrime(i)) results.add(i);
        }
        return results;
    }
}
