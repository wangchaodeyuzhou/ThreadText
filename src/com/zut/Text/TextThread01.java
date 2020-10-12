package com.zut.Text;

public class TextThread01 implements Runnable {
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("我要吹牛逼你服不服"+i);
        }
    }
}
