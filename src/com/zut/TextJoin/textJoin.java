package com.zut.TextJoin;

public class textJoin implements Runnable {

    @Override
    public void run() {
      for (int i=0;i<100;i++){
          System.out.println("线程运行"+i);
      }
    }

    public static void main(String[] args) throws InterruptedException {
        textJoin textJoin = new textJoin();
        Thread join = new Thread(textJoin, "join线程");
        join.start();
        for (int i=0;i<1000;i++){
            if(i==100) join.join();
            System.out.println("main线程运行"+i);
        }
    }
}
