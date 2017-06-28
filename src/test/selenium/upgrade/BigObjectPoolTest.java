package test.selenium.upgrade;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuanyuanyang1 on 6/27/2017.
 */
public class BigObjectPoolTest {
    public static void main(String[] args){
        long start = System.nanoTime();
        for(int i = 0 ; i < 10000 ; i++){
            BigObjectPool.getBigObject("xxx" , true);
        }
        System.out.println("the time cost of using BigObjectPool : " +
                TimeUnit.MILLISECONDS.convert(System.nanoTime() - start , TimeUnit.NANOSECONDS) + " milliseconds");
        //millisecond:毫秒

        start = System.nanoTime();
        for(int i = 0 ; i < 10000 ; i++){
            BigObjectPool.getBigObject("xxx" , false);
        }
        System.out.println("the time cost of no use BigObjectPool : " +
                TimeUnit.MILLISECONDS.convert(System.nanoTime() - start , TimeUnit.NANOSECONDS) + "milliseconds");
    }
}

class BigObjectPool{
    private static Map<String , BigObject> map = new HashMap<String , BigObject>();
    static{
        map.put("xxx" , new BigObject("xxx"));
        map.put("yyy" , new BigObject("yyy"));
    }
    public static BigObject getBigObject(String key , boolean userPool){
        if(userPool){
            BigObject bo = map.get(key);
            if(bo == null){
                bo = new BigObject(key);
            }
            return bo;
        }else{
            return new BigObject(key);
        }
    }
}

class BigObject{
    private String name;
    private byte[] data = new byte[1024 * 1024];
    public BigObject(String name){this.name = name;}
}