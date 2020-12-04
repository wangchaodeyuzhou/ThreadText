package com.zut.System.Text4;

/**
 * @author 王朝的宇宙
 * @version V1.0
 * @Package com.zut.System.Text04
 * @date 2020/12/4 20:18
 */
import sun.misc.Signal;
import sun.misc.SignalHandler;
@SuppressWarnings("restiction")
public class helloWorld implements SignalHandler{

   private void Callback(Signal signal){

       try {
           if(signal.getName().equals("TERM")){
               System.out.println("quiting");
               System.exit(0);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    public static void main(String[] args) {
         helloWorld t =new helloWorld();
         Signal.handle(new Signal("TERM"),t);

         while (true){
             try {
                 Thread.sleep(3000);
                 System.out.println("helloword______");
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

         }
    }

    @Override
    public void handle(Signal signal) {
        Callback(signal);
    }
}
