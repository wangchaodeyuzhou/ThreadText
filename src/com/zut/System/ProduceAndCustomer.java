package com.zut.System;

import java.util.LinkedList;

public class ProduceAndCustomer {
      //测试
      public static void main(String[] args) {
          ProduceAndCustomer produceAndCustomer = new ProduceAndCustomer();
          Producer producer = produceAndCustomer.new Producer();
          Customer customer = produceAndCustomer.new Customer();

          for (int i = 0; i < 10; i++) {
              Thread threadpro = new Thread(producer,"生产者"+i);
              Thread threadcon = new Thread(customer,"消费者"+i);
              threadpro.start();
              threadcon.start();
          }
      }




    //定义一个最大的容量
    public static final int MAX_Size=2;
    //定义一个存储媒介
    //这个是线程不安全的
    public static LinkedList<Integer> list=new LinkedList<>();
    //定义生产者为内部类
    class  Producer implements Runnable {


        @Override
        public void run() {
            //判断仓库是否已经满了
          //  synchronized (list){
                while (list.size() == MAX_Size) {
                    System.out.println("仓库已满，生产者" + Thread.currentThread().getName() + "不可生产");
                    try {
                        list.wait();//将该生产者对象放入阻塞队列
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(1);
                System.out.println("生产者" + Thread.currentThread().getName() + "生产，仓库的容量为" + list.size());
                //
                list.notify();
          //  }
        }
    }
        class Customer implements Runnable{

            @Override
            public void run() {
                //判断仓库是否为空
//                synchronized (list){
                    while (list.size() == 0){
                        System.out.println("仓库为空，消费者"+Thread.currentThread().getName()+"停止消费");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list.removeFirst();
                    System.out.println("消费者"+Thread.currentThread().getName()+"消费，仓库的容量为"+list.size());
                    list.notify();
                }
            //}
        }

}
