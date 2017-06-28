package test.selenium.upgrade;

/**
 * Created by yuanyuanyang1 on 6/27/2017.
 */
import java.util.HashSet;

public class Singleton {
    private Singleton(){}
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }
    public void doSomthing(){}

    public static void main(String[] args){
        HashSet<Integer> hs = new HashSet<>();
        //根据hashCode确定元素位置，Integer的hashCode和本身value相等
        /*
        //Integer hashCode源码：
        public static int hashCode(int value) {
            return value;
        }
        */
        hs.add(7);
        hs.add(-2);
        hs.add(9);
        System.out.println(hs);

        HashSet<String> hs1 = new HashSet<>();
        /*
        //String hashCode()源码：
        public int hashCode() {
            int h = hash;
            if (h == 0 && value.length > 0) {
                char val[] = value;

                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                    hash = h;
             }
             return h;
        }
         */
        hs1.add("y1");
        hs1.add("y2");
        hs1.add("a1");
        System.out.println(hs1);
    }
}
