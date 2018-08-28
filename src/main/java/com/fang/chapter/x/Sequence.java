package com.fang.chapter.x;

public class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence(int size){ items = new Object[size]; }
    public void add(Object x){
        if(next < items.length){
            items[next++] = x;
        }
    }
    private class SequenceSelector implements Selector{
        private int i = 0;
        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if(i < items.length)
                i++;
        }
    }
    public Selector selector(){
        return new SequenceSelector();
    }

    public static void main(String[] args) {

        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            Person.Inner inner = new Person(Integer.toString(i)).getInner();
            sequence.add(inner);
        }
        Selector selector = sequence.selector();
        while(!selector.end()){
            System.out.println(selector.current().toString()+"\t");
            selector.next();
        }

//        Sequence sequence = new Sequence(10);
//        for (int i = 0; i < 10; i++) {
//            sequence.add(new Person(Integer.toString(i)));
//        }
//        Selector selector = sequence.selector();
//        while(!selector.end()){
//            System.out.println(selector.current().toString()+"\t");
//            selector.next();
//        }
//        Sequence sequence = new Sequence(10);
//        for (int i = 0; i < 10; i++)
//            sequence.add(Integer.toString(i));
//        Selector selector = sequence.selector();
//        while(!selector.end()){
//            System.out.print(selector.current()+"\t");
//            selector.next();
//        }
    }
}
interface Selector{
    boolean end();
    Object current();
    void next();
}
class Person{
    private String name;
    public Inner getInner(){
        return new Inner();

    }    class Inner{

        public String toString(){
            return "Hello World! " + name;
        }

    }

    public Person(String name){
        this.name = name;
    }
}