package com.lovetogether.thinkinjava.chapter.xi.queue;

import javax.management.Query;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueDemo {

    public static void printQ(Queue queue) {
        while(queue.peek() != null) {
            System.out.print(queue.remove() + "   ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            printQ(queue);
            Queue<Character> qc = new LinkedList<>();
            for (Character c : "Brontosaurus".toCharArray()) {
                qc.offer(c);
                printQ(qc);
            }

        }
    }

}
