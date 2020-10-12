package com.zut.lambdaException;

public class Text01 {
    public static void main(String[] args) {
//        Love love = (a) -> {
//            System.out.println("我是"+a);
//        };
//        love.Love1("去你妈");
        Love love = a -> System.out.println("我是"+a);
        love.Love1("去你妈");
    }
}
interface Love{
    void Love1(String love);
}