package com.fang.thinkinjava.chapter.xi.practice.twenty.eight;

import java.util.PriorityQueue;
import java.util.Random;

public class TestPriorityQueue {

    public static void main(String[] args) {
        Random random = new Random(47);
        PriorityQueue<Double> queue = new PriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            double d = random.nextDouble();
            queue.offer(d);
        }
        while(queue.peek() != null) {
            System.out.println(queue.peek());
        }

        PriorityQueue<Person> personPriorityQueue = new PriorityQueue<>();
        personPriorityQueue.offer(new Person());
    }
}

class Person {

}
