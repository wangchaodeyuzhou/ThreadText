package com.zut.System;

public class deadlocak {

    public static void main(String[] args) {

        StringBuffer m1 = new StringBuffer(); //线程安全的
        StringBuffer m2 = new StringBuffer();
        StringBuffer m3 = new StringBuffer();
        //

        //p1线程
        new Thread(() -> {
            int  count = 1000;
            while (count > 0) {
                synchronized (m1) {
                    try {
                        m1.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                System.out.println(count);
                //申请m3的锁
                synchronized (m3) {
                    m3.notify();
                }
            }
        }).start();

        //p3线程
        new Thread(() -> {
            int  count = 1000;
            while (count > 0) {
                synchronized (m3) {
                    try {
                        m3.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                System.out.println(count);
                //申请m2的锁
                synchronized (m2) {
                    m2.notify();
                }
            }
        }).start();
       //p2线程
        new Thread(()->{
            int  count = 1000;
            while (count>0){
                synchronized (m2){
                    try {
                        m2.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                System.out.println(count);
                //申请m1的锁
                synchronized (m1){
                    m1.notify();
                }
            }
        }).start();

    }
}
