package api.myjuc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、i++ 的原子性问题：i++的操作实际上分为三步骤：1、读 2、改 3、写
 *      int i = 10;
 *      i = i++;//resule: 10
 *
 * 步骤：
 *      int temp = i;
 *      i = i + 1;
 *      i = temp
 *
 * 二、原子变量：jdk1.5后，java.util.concuttent.atomic包下提供了常用的原子变量
 *            1、volatile：值都被volatile修饰，保证内存可见性
 *            2、CAS（Compare-And-Swap）算法保证数据的原子性
 *              CAS算法是操作硬件对并发操作共享数据的支持
 *              CAS包含了三个操作数：
 *                  1、内存值 V
 *                  2、预估值 A
 *                  3、更新的值 B
 *               当且仅当V == A时，V = B， 否则 将不做任何操作
 *               与锁相比效率较高，原因是：一次失败，不会阻塞，直到成功
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }

}

class AtomicDemo implements Runnable{

//    private int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : " +getSerialNumber());
    }

    public int getSerialNumber(){
//        return serialNumber++;
        return serialNumber.getAndIncrement();
    }
}
