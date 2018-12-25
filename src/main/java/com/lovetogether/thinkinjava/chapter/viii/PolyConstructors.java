package com.lovetogether.thinkinjava.chapter.viii;

public class PolyConstructors{
    public static void main(String[] args) {
        new RoundGlyph(5);
//        new RectangularGlyph();
    }
}

class RoundGlyph extends Glyph{
    private int radius = 1;
    RoundGlyph(int r){
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(), radiud = " + radius);
    }
    void draw(){
        System.out.println("RoundGlyph.draw, radius = " + radius);
    }

}
class Glyph{
    void draw(){
        System.out.println("Glyph.draw()");
    }
    Glyph(){
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}
class RectangularGlyph extends Glyph{
    private int i = 1;
    public RectangularGlyph() {
        System.out.println("RectangularGlyph.RectangularGlyph().i = " + i);
        draw();
    }
    void draw(){
        System.out.println("RectangularGlyph.draw()");
    }
}
