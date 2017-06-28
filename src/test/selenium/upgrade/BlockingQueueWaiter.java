package test.selenium.upgrade;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yuanyuanyang1 on 6/28/2017.
 */
public class BlockingQueueWaiter {
    static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);


    public static void main(String[] args) throws InterruptedException{
        BlockingQueueWaiter busy = new BlockingQueueWaiter();
        for(int i = 0 ; i < 10 ; i++){
            Thread consumer = new Thread(){
                @Override
                public void run(){
                    String dishes = null;
                    try{
                        System.out.printf("顾客尝试取走菜%n");
                        dishes = queue.take();
                        System.out.printf("顾客取走菜：%s%n" , dishes);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            consumer.start();
        }
        Thread.sleep(100);
        for(int i = 0 ; i < 10 ; i++){
            Thread chef = new Thread(){
                @Override
                public void run(){
                    String dishes = "糖醋鱼";
                    try{
                        System.out.printf("厨师尝试交菜%n");
                        queue.put(dishes);
                        System.out.printf("厨师交菜：%s%n" , dishes);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            chef.start();
        }
    }
}
