package com.zut.System;

import java.util.concurrent.Semaphore;

public class Text02 {
    public static Integer count = 0;

    //创建三个信号量 Full,empty, mutex
    private final Semaphore Full = new Semaphore(10);
    private final Semaphore Empty = new Semaphore(0);
    private final Semaphore mutex = new Semaphore(1);
    //测试
    public static void main(String[] args) {
        Text02 text02 = new Text02();
        Producer producer = text02.new Producer();
        Constomer constomer = text02.new Constomer();
        for (int i = 0; i < 2; i++) {
            new Thread(producer,"生产线程"+i).start();
            new Thread(constomer,"消费线程"+i).start();
        }
    }
    //生产者
    class Producer implements Runnable{

        @Override
        public void run() {
          for (int i=0;i<5;i++){
              try {
                  Thread.sleep(3000);
                  Full.acquire();
                  mutex.acquire();
                  count++;
                  System.out.println(Thread.currentThread().getName()+"生产者生产，目前总共有"+count);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }finally {
                  mutex.release();
                  Empty.release();
              }
          }
        }
    }

    //消费者
    class Constomer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                    Empty.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName()+"消费者消费，目前总共有"+count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    mutex.release();
                    Full.release();
                }
            }
        }
    }
}
