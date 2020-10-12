package com.zut.System;

import com.zut.wangchaodeyuzhou.Produce;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Text03 {
    public static final Integer full = 2;
    public static  Integer count = 0;
    //创建锁对象
    private Lock lock = new ReentrantLock();
    //创建两个条件变量，
    private final Condition Full = lock.newCondition();
    private final Condition Empty = lock.newCondition();
    //测试
    public static void main(String[] args) {
        Text03 text03 = new Text03();
        Produce produce = text03.new Produce();
        Costomer costomer = text03.new Costomer();
        for (int i = 0; i < 2; i++) {
            new Thread(produce,"生产者线程"+i).start();
            new Thread(costomer,"消费者线程"+i).start();
        }

    }
    //生产者
    class Produce implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                    lock.lock();//获取锁
                    while (count == full){
                        Full.await();
                    }
                     count++;
                    System.out.println(Thread.currentThread().getName()
                            +"生产者生产， 目前总共有" +count
                            );
                     Empty.signal();//唤醒消费者
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();//释放锁
                }
            }
        }
    }

    //消费者
    class Costomer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {

                try {
                    Thread.sleep(3000);
                    lock.lock(); //上锁
                    while (count == 0){
                        Empty.await();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName()
                            +"消费者消费，目前总共有"+count);
                    Full.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();//释放锁
                }

            }
        }
    }
}
