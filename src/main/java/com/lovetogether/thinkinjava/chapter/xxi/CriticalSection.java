package com.lovetogether.thinkinjava.chapter.xxi;

class Pair {// Not thread safe
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }

    public class PairValuesNotEqualsException extends Exception {
        public PairValuesNotEqualsException() {
            super("");
        }
    }
}

public class CriticalSection {
}
