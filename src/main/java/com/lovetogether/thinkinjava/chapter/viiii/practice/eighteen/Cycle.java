package com.lovetogether.thinkinjava.chapter.viiii.practice.eighteen;

public class Cycle {
    public static void getCycle(CycleFactory cf){
        MyCycle cycle = cf.getCycle();
        cycle.riding();
    }
    public static void main(String[] args) {
        Cycle.getCycle(new UnicycleFactory());
        Cycle.getCycle(new BicysleFactory());
        Cycle.getCycle(new TricycleFactory());
    }
}

interface MyCycle{
    void riding();
}
interface CycleFactory{
    MyCycle getCycle();
}
class Unicycle implements MyCycle{
    public void riding(){
        System.out.println("Unicycle is riding");
    }
}
class UnicycleFactory implements CycleFactory{
    public MyCycle getCycle(){
        return new Unicycle();
    }
}
class Bicysle implements MyCycle{
    public void riding(){
        System.out.println("Bicysle is riding");
    }
}
class BicysleFactory implements CycleFactory{
    public MyCycle getCycle(){
        return new Bicysle();
    }
}
class Tricycle implements MyCycle{
    public void riding(){
        System.out.println("Tricycle is riding");
    }
}
class TricycleFactory implements CycleFactory{
    public MyCycle getCycle(){
        return new Tricycle();
    }
}