package test.selenium.upgrade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yuanyuanyang1 on 6/27/2017.
 */
public class HashMapTest {
    public static void main(String[] args) throws InterruptedException{
        HashMap<String , String> map = new HashMap<>();
        map.put("a" , "a");
        Iterator it = map.keySet().iterator();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                map.put("b" , "b");
            }
        });
        exec.shutdown();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
