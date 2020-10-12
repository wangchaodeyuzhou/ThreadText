package com.zut.Text;

public class textThread  extends Thread {
    @Override
    public void run() {
        //run方法线程体
        //super.run();
       for (int i=0;i<20;i++){
           System.out.println("我在看代码》》》》"+i);
       }
    }

    public static void main(String[] args) {
        new textThread().start();
        //表示测试方法与main方法同时执行
        for(int i=0;i<2000;i++){
            System.out.println("我在学习多线程...."+i);
        }
    }
}
