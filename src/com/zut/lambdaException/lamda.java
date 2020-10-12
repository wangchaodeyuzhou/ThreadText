package com.zut.lambdaException;

public class lamda {
   //  3.静态内部类
  static   class like1 implements ILike{
       @Override
       public void lamba() {
           System.out.println("I iiiii");
       }
   }


    //函数式编程
    //函数式接口
    //前提条件式一个接口只有一个抽象式函数
    //如果有两个，那么就对不起了，你无法使用lambda表达式
    public static void main(String[] args) {
        ILike like = new like();
        //接口调用他的实现类
        like.lamba();
        //调用静态内部类
        like1 like1 = new like1();
        like1.lamba();
        //4 局部内部类
        class like2 implements ILike{
            @Override
            public void lamba() {
                System.out.println("局部内部类");
            }
        }
        like2 like2 = new like2();
        like2.lamba();
        // 5 匿名内部类
       ILike like3 = new ILike(){

            @Override
            public void lamba() {
                System.out.println("匿名内部类");
            }
        };
       like3.lamba();
       // 6.搞一下lambda的简化
        like = ()->{
            System.out.println("lambda简化");
        };
        like.lamba();
    }



}
// 定义一个接口
interface ILike{
    void lamba();
}
class like implements ILike{
    @Override
    public void lamba() {
        System.out.println("I LOVE LAMBDA");
    }
}
