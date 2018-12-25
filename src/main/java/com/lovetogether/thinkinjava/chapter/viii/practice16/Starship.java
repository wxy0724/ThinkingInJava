package com.lovetogether.thinkinjava.chapter.viii.practice16;

public class Starship {
    private AlterStatus status = new AlterStatus();
    public void change(AlterStatus nowStatus){
        status = nowStatus;
    }
    public void displayStatus(){
        status.displayStatus();
    }

    public static void main(String[] args) {
        Starship starship = new Starship();
        starship.displayStatus();
        starship.change(new Fly());
        starship.displayStatus();
        starship.change(new Combat());
        starship.displayStatus();
    }
}
class AlterStatus{
    public void displayStatus(){
        System.out.println("Waitting");
    }
}
class Combat extends AlterStatus{
    public void displayStatus(){
        System.out.println("Combating");
    }
}
class Fly extends AlterStatus{
    public void displayStatus(){
        System.out.println("Flying");
    }
}
