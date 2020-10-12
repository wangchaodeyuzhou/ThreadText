package com.zut.Text;

public  class  textThread03 implements Runnable {
     static int ticket = 10;
    @Override
    public  void run() {
      while (true){
          if(ticket<=0) break;
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
          }
          System.out.println(Thread.currentThread().getName()+"抢到票"+ticket--);
      }
    }
}
class tt{
    public static void main(String[] args) {
        textThread03 textThread03 = new textThread03();

        new Thread(textThread03,"小刚").start();
        new Thread(textThread03,"小米").start();
        new Thread(textThread03,"民吗").start();
    }
}
