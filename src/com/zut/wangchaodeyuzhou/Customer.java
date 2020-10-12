package com.zut.wangchaodeyuzhou;

public class Customer implements Runnable {
    Box box;
    public Customer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        while (true){
            synchronized (box){
                box.getPoint = box.getPoint%box.boxLength;
                if(box.boxCount==0){//表示盒子里的东西空了，没有可以吃的了
                    try {
                        box.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    box.buff[(++box.getPoint)%box.boxLength] = 0;
                    //0 表示东西不在了
                    System.out.println(Thread.currentThread().getName()+"消费了一个东西");
                    box.boxCount--;
                    System.out.println("还剩东西总数为"+box.boxCount);
                    box.notify();//唤醒其他消费者
                    //添加一秒的延时
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
