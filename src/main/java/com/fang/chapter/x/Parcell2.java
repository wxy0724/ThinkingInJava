package com.fang.chapter.x;

public class Parcell2 {
    class Contents{
        private int i = 11;
        public int value(){ return i; }

    }
    class Destination{
        private String label;
        Destination(String whereTo){
            label = whereTo;
        }
        String readLabel(){ return label; }
    }
        public Destination to(String s ){
            return new Destination(s);
        }
        public Contents contents(){
            return new Contents();
        }
        public void ship(String dest){
            Contents c = contents();
            Destination d = to(dest);
            System.out.println(d.readLabel());
        }

    public static void main(String[] args) {
        Parcell2 p = new Parcell2();
        p.ship("Tasmania");
        Parcell2 q = new Parcell2();
        Parcell2.Contents c = q.contents();
        Parcell2.Destination d = q.to("Borner");
    }
}
