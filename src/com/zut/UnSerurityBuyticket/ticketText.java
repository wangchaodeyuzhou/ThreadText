package com.zut.UnSerurityBuyticket;

public class ticketText {
    public static void main(String[] args) {
        Buyticket buyticket = new Buyticket();
        new Thread(buyticket,"苦逼的我").start();
        new Thread(buyticket,"你们好").start();
        new Thread(buyticket,"科比").start();
    }


}
class Buyticket implements Runnable{
    private int ticket =10;
    private  Boolean running = true;

    @Override
    public void run() {
        while (running){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void buy() throws InterruptedException {
        if (ticket <= 0) {
            running =false;
            return;
        }
        //模拟延时
        Thread.sleep(1000);
        //买票
        System.out.println(Thread.currentThread().getName()+"买了票为"+ticket--);
    }
}
