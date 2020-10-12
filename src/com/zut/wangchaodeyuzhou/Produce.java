package com.zut.wangchaodeyuzhou;
//生产者
public class Produce implements Runnable {
    Box box;
    public Produce(Box box) { //构造器及时更新box对象
        this.box = box;
    }

    @Override
    public void run() {
        while (true) {

               box.putPoint = box.putPoint % box.boxLength;
               if(box.boxCount == box.boxLength){//生产的盒子里面已经full
                   try {
                       box.wait();//开始停止生产让消费者消费
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }else{
                   box.buff[(++box.putPoint)%box.boxLength] = 1;
                   //数组元素的值等于1表示已经放入生产的东西了
                   box.boxCount++;//表示生产的个数
                   System.out.println(Thread.currentThread().getName()+"生产一个东西");
                   System.out.println("生产的总数为"+box.boxCount);
                   box.notify();//可以唤醒生产者继续生产

                   //添加一秒的延迟便于观察
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
           }
        }
    }
}
