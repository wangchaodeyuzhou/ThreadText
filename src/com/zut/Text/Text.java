package com.zut.Text;

public class Text {
    public static void main(String[] args) {
        Thread thread = new Thread(new TextThread01());
        thread.start();

        for (int i=0;i<100;i++){
            System.out.println("你还来拉斯的把"+i);
        }


    }
}
