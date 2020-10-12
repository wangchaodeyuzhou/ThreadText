package com.zut.State;

public class Textfamit  implements Runnable{
    //设置一个标志类
    public  boolean flag = true;
    public static void main(String[] args) {
        Textfamit textfamit = new Textfamit();
        Thread thread = new Thread(textfamit);
        thread.start();
        for (int i=0;i<1000;i++){
            System.out.println("main还在跑");
            if(i==900){
                textfamit.stop();
                System.out.println("该线程停止了");
            }
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("Threaded.....run"+ i++);
        }
    }
    //stop
    //设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag = false;
    }

}
