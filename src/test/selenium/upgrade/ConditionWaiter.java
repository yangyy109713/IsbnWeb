package test.selenium.upgrade;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yuanyuanyang1 on 6/28/2017.
 */
public class ConditionWaiter {
    private String dishes = null;
    private Lock lock = new ReentrantLock();
    private Condition conConsumer = lock.newCondition();
    private Condition conChef = lock.newCondition();

    public String getDishes(){
        try{
            lock.lock();
            System.out.printf("顾客得到服务员锁%n");
            while(this.dishes == null){
                try{
                    System.out.printf("顾客取菜，没有菜，顾客线程等待（释放锁）%n");
                    conConsumer.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            String d = this.dishes;
            System.out.printf("顾客取走菜：%s%n", this.dishes);
            this.dishes = null;
            conChef.signal();
            System.out.printf("服务员通知正在等待的线程（厨师）%n");
            return d;
        }finally {
            lock.unlock();
        }

    }

    public void setDishes(String dishes){
        try{
            lock.lock();
            System.out.printf("厨师得到服务员锁%n");
            while(this.dishes != null){
                try{
                    System.out.printf("厨师交菜，但服务员手里有另一盘菜，厨师线程等待（释放锁）%n");
                    conChef.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            this.dishes = dishes;
            System.out.printf("厨师交菜：%s%n", this.dishes);
            conConsumer.signal();
            System.out.printf("服务员通知正在等待的线程（顾客）%n");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ConditionWaiter busy = new ConditionWaiter();
        for(int i = 0 ; i < 10 ; i++){
            Thread consumer = new Thread(){
                @Override
                public void run(){
                    busy.getDishes();
                }
            };
            consumer.start();
        }
        Thread.sleep(100);
        for(int i = 0 ; i < 10 ; i++){
            Thread chef = new Thread(){
                @Override
                public void run(){
                    String dishes = "宫保鸡丁";
                    busy.setDishes(dishes);
                }
            };
            chef.start();
        }
    }
}
