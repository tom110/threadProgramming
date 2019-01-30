/*
* 脏读现象，原因是写加锁，读不加锁
* */
import java.util.concurrent.TimeUnit;

public class Thread008 {
    String name;
    double account;

    public synchronized void setAccount(String name,double account){
        this.name=name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.account=account;

    }
    public /* synchronized */ double getAccount(){
        return this.account;
    }

    public static void main(String[] args){
        Thread008 t=new Thread008();
        new Thread(()->t.setAccount("zhang3",2.00)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getAccount());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getAccount());
    }
}
