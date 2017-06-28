package test.selenium.upgrade;

/**
 * Created by yuanyuanyang1 on 6/28/2017.
 */
public class Waiter {
    private String dishes = null;

    public synchronized String getDishes() {
        System.out.printf("顾客得到服务员锁%n");
        while (this.dishes == null) {
            try {
                System.out.printf("顾客取菜，没有菜，顾客线程等待（释放锁）%n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String d = this.dishes;
        System.out.printf("顾客取走菜：%s%n", this.dishes);
        this.dishes = null;
        //notify();//随机唤醒一个等待的线程
        notifyAll();//唤醒所有等待的线程
        System.out.printf("服务员通知正在等待的线程（厨师）%n");
        return d;
    }

    public synchronized void setDishes(String dishes) {
        System.out.printf("厨师得到服务员锁%n");
        while (this.dishes != null) {
            try {
                System.out.printf("厨师交菜，但服务员手里有另一盘菜，厨师线程等待（释放锁）%n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.dishes = dishes;
        System.out.printf("厨师交菜：%s%n", this.dishes);
        //notify();//随机唤醒一个等待的线程
        notifyAll();//唤醒所有等待的线程
        System.out.printf("服务员通知正在等待的线程（顾客）%n");
    }

    public static void main(String[] args) throws InterruptedException{
        Waiter busy = new Waiter();
        //for (int i = 0; i < 1; i++) {
        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread() {
                @Override
                public void run() {
                    busy.getDishes();
                }
            };
            consumer.start();
        }
        Thread.sleep(100);
        /*
        执行后会发现程序会陷入永久的等待无法结束，这是因为notify()方法只唤醒众多等待的线程中的一个，拿到菜后本应唤醒顾客取走，
        但是有可能随机唤醒了另一个等待的厨师，没有顾客能取走服务员手中的菜，这时候程序就无法继续下去了。
         */
        //for (int i = 0; i < 1; i++) {
        for (int i = 0; i < 10; i++) {
            Thread chef = new Thread() {
                @Override
                public void run() {
                    String dishes = "雪菜肉丝面";
                    busy.setDishes(dishes);
                }
            };
            chef.start();
        }
    }
}