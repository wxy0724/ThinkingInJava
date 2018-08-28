package api.myjuc;

/**
 * 1、两个普通同步方法，两个线程，标准打印
 * 2、新增Thread.sleep（3000）给getOne。结果one,two
 * 3、新增一个普通方法getThree()，打印three,one,two
 * 4、两个普通的同步方法，两个Number对象two,one
 * 5、修改getOne为静态同步方法，打印two,one
 * 6、修改两个方法均为静态同步方法，一个number对象,one,two
 * 7、一个静态同步方法，一个非静态同步方法，两个Number对象,two,one
 * 8、两个静态同步方法，两个Number对象，one,two
 *
 * 线程八锁的关键：
 * ① 非静态方法的锁默认为this,静态方法的锁为对应的Class实例
 * ② 某一个时刻内，只能有一个线程持有锁，不管有多少个方法
 *
 */
public class TestThread8Monitor {

    public static void main(String[] args) {

        Number number = new Number();
        Number number2 = new Number();

        new Thread(() -> number.getOne()).start();

        new Thread(() -> number2.getTwo()).start();

//        new Thread(() -> number.getThree()).start();
    }

}

class Number {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }

//    public void getThree() {
//        System.out.println("three");
//    }
}
