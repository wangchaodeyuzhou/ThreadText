package com.zut.TextYield;

public class text {

    public static void main(String[] args) {
        myYeild myYeild = new myYeild();
       new Thread(myYeild,"a").start();
       new Thread(myYeild,"b").start();
    }

}
class myYeild implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"线程停止");
    }
}