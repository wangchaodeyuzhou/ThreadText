package com.zut.wangchaodeyuzhou;

public class text01 {
    public static void main(String[] args) {
       Box box =new Box();
       Thread p1=new Thread(new Produce(box),"生产者1");
       Thread p2=new Thread(new Produce(box),"生产者2");
       Thread c = new Thread(new Customer(box),"消费者");
       p1.start();
       p2.start();
       c.start();
    }
}
class Box{
    int boxLength = 10;//盒子的最大容量
    int buff[] = new int[boxLength];
    int putPoint = 0;
    int getPoint = 0;
    int boxCount = 0;
}