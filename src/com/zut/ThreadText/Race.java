package com.zut.ThreadText;
//模拟赛跑
public class Race implements Runnable {
    //标识胜利者
  private static String winner;


    @Override
    public void run() {


        for (int i=0;i<=100;i++){
            //模拟兔子休息
            if (Thread.currentThread().getName().equals("小兔子")&&i%10==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //判断游戏是否结束
            boolean flag = gameOver(i);
            if (flag) break;
            System.out.println(Thread.currentThread().getName()+"-->跑里"+i+"米");
        }
    }

    //判断赛事是否出现成功者
    private boolean gameOver(int steps){
        if(winner!=null) return true; //出现了胜利者了
        if(steps >=100){
            winner =Thread.currentThread().getName();
            System.out.println("当前的胜利者是"+winner);
            return true;
        }
        return false; //没有出现胜利者
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"小兔子").start();
        new Thread(race,"小乌龟").start();
    }
}
