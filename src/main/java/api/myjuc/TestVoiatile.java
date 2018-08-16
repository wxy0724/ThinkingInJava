package api.myjuc;

/**
 * voiatole 关键字：多个线程访问共享数据时，可以保证内存中的数据是可见的
 * 轻量级的同步策略
 * 相较于synchronized是一种较为 轻量级的同步策略
 * 区别：
 * 1、volatile不具备“互斥性”
 * 2、volatile不能保证变量的原子性
 */
public class TestVoiatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while(true){
//            synchronized (td){
                if (td.isFlag()){
                    System.out.println("----------------");
                    break;
                }
//            }
        }

    }
}

/**
 * 回顾
 */
class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        System.out.println("flag = " + isFlag());
    }
}