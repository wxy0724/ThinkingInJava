package testapi;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println(list.size());
        list.remove(0);
        System.out.println(list.size());

//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> System.out.println(random.nextInt(3))).start();
//        }

    }

}
