package test.selenium.upgrade;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yuanyuanyang1 on 6/27/2017.
 */
public class ArrayListDemo implements Runnable{
    static ArrayList<Integer> list = new ArrayList<>();
    static CountDownLatch latch = new CountDownLatch(10000);

    @Override
    public void run(){
        list.add(1);
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10000 ; i++){
            es.execute(new ArrayListDemo());
        }
        latch.await();
        System.out.println("list.size() = " + list.size());
    }
}
