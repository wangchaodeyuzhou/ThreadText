package com.zut.System;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Text04 {
   final PipedInputStream pis = new PipedInputStream();
   final PipedOutputStream pos = new PipedOutputStream();

    {
        try {
            pis.connect(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试
    public static void main(String[] args) {
        Text04 text04 = new Text04();
        Producer producer = text04.new Producer();
        Customer customer = text04.new Customer();
        new Thread(producer,"生产者").start();
        new Thread(customer,"消费者").start();

    }

    //生产者
    class Producer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                while (true){
                        Thread.sleep(1000);
                        int num = (int) (Math.random()*255);
                        System.out.println(Thread.currentThread().getName()+
                                "生产者生产一个数字为 "+num);
                        pos.write(num);
                        pos.flush();
                    }
                } catch (InterruptedException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }finally {
                    try {
                        pos.close();
                        pis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    class Customer implements Runnable{

        @Override
        public void run() {
          try {
              while (true){
                  Thread.sleep(3000);
                  int num = pis.read();
                  System.out.println(Thread.currentThread().getName()
                          +"消费者消费了一个数字为 "+num);
              }
          }catch (InterruptedException e) {
              e.printStackTrace();
          }catch (IOException e) {
              e.printStackTrace();
          }finally {
              try {
                  pos.close();
                  pis.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
        }
    }
}
