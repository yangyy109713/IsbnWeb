package test.selenium.upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuanyuanyang1 on 6/27/2017.
 */
public class ListTest {
    public static long getTime(List<Integer> list , int index){
        long time = System.nanoTime();
        for(int i = 0 ; i < 100000 ; i++){
            list.add(index , i);
        }
        return TimeUnit.MILLISECONDS.convert(System.nanoTime() - time , TimeUnit.NANOSECONDS);
    }

    public static void main(String[] args){
        List<Integer> array1 = new ArrayList<Integer>() , array2 = new ArrayList<Integer>();
        List<Integer> link1 = new LinkedList<Integer>() , link2 = new LinkedList<Integer>();
        for(int i = 0 ; i < 100000 ; i++){
            array1.add(i) ;
            array2.add(i);
            link1.add(i) ;
            link2.add(i);
        }
        System.out.println("from 0 to 50000 ArrayList costs: " + getTime(array1 , 0));
        System.out.println("from 0 to 50000 LinkedList costs: " + getTime(link1 , 0));
        System.out.println("from 50000 to 100000 ArrayList costs: " + getTime(array2 , 50000));
        System.out.println("from 50000 to 100000 LinkedList costs: " + getTime(link2 , 50000));
        /*
        the result is :
        from 0 to 50000 ArrayList costs: 4462
        from 0 to 50000 LinkedList costs: 18
        from 50000 to 100000 ArrayList costs: 2610
        ArrayList内部使用Object数组保存，数组在内存中是连续的线性空间，根据索引查询速度快，但是插入、删除，可能要移动大量的元素，速度较慢；
        LinkedList采用双向链表结构存储，第二次测试选定从索引50000开始，LinkedList每次操作前都需要从0开始寻址到50000，查询消耗大量时间，所以实际执行很慢。
        LinkedList可以当作Stack和Queue使用，这是ArrayList不具备的。
         */
    }
}
