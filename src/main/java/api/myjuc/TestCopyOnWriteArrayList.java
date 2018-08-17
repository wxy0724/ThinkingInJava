package api.myjuc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList/CopyOnWriteArraySet:写入并复制
 *
 * 注意：
 * 每次写入时都会复制，效率较低，适当使用，添加多时不适合，并发迭代操作多，适合
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread ht = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(ht).start();
        }
    }

}

class HelloThread implements Runnable {

//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();

    static {
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());

            list.add("AA");
        }
    }
}
