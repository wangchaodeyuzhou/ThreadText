package com.zut.System;

import java.util.concurrent.Semaphore;

public class SolverDeadLocak {
    public static void main(String[] args) {
        Semaphore m1 = new Semaphore(1);
        Semaphore m2 = new Semaphore(0);
        Semaphore m3 = new Semaphore(0);
        //p1线程
        new Thread(()->{
            int count = 1000;
            while (count>0){
                try {
                    m1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println(count);
                m3.release();
            }
        }).start();
        //p3线程
        new Thread(()->{
            int count = 1000;
            while (count>0){
                try {
                    m3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println(count);
                m2.release();
            }
        }).start();
        //p2线程
        new Thread(()->{
            int count =1000;
            while (count>0){
                try {
                    m2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println(count);
                m1.release();
            }
        }).start();
    }
}
