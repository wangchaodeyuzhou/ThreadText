package com.zut.StaticPorxy;

import java.util.Map;

public class textMarry {
    public static void main(String[] args) {
        //这个Thread就像是代理对象和真实的对象都是实现了Runnable接口
        new Thread(()-> System.out.println("I LOVE YOU"));
        //you是真实对象
        //weddincompany是静态代理对象
        //代理对象可以做真实对象做不了的事
        //真实对象子要专心做自己的事
        WeddinCompany weddinCompany = new WeddinCompany(new You());
        weddinCompany.toMarray();
    }
}
 interface Marry{
  public void toMarray();
}
class You implements Marry{

    @Override
    public void toMarray() {
        System.out.println("我要结婚了，我好开心");
    }
}
class WeddinCompany implements Marry{
    Marry tarrget;

    public WeddinCompany(Marry tarrget) {
        this.tarrget = tarrget;
    }

    @Override
    public void toMarray() {
        before();
        tarrget.toMarray();
        after();
    }

    private void before() {
        System.out.println("结婚前，布置现场");
    }

    private void after() {
        System.out.println("结婚后，收尾款");
    }

}
