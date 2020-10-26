package com.zut.Unsafe;

public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        //new Drawing(account,50,"you").start();
        new Drawing(account,100,"wifi").start();
        new Drawing(account,50,"you").start();
    }
}

class Account {

    public int meony;
    public  String name;

    public Account(int meony, String name) {
        this.meony = meony;
        this.name = name;
    }

    public int getMeony() {
        return meony;
    }

    public void setMeony(int meony) {
        this.meony = meony;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//取款
class Drawing extends Thread {
    //账户
    Account account;
    //取钱
    int drawingMenony;
    //存钱
    int nowMenoy;

    public Drawing(Account account, int drawingMenony, String name) {
        super(name);
        this.account = account;
        this.drawingMenony = drawingMenony;

    }

    @Override
    public void run() {
        //判断卡里是否有钱
       if (account.meony - drawingMenony <= 0 ){
           System.out.println(Thread.currentThread().getName()+"对不起，你的账户余额不足");
           return;
       }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.meony = account.meony - drawingMenony;
       nowMenoy = nowMenoy + drawingMenony;
        System.out.println(this.getName()+"你的余额为"+account.meony);
        System.out.println(this.getName()+"你手里的钱是"+nowMenoy);
    }
}
