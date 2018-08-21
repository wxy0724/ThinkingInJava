package com.fang.chapter.viiii;

import java.util.Random;

public interface RandVals {
    Random RAND = new Random(47);
    int RANDOM_INT = RAND.nextInt(10); //[0,n)
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RANDOM_FLOAT = RAND.nextFloat() * 10;
    double RANDOM_DOUBLE = RAND.nextDouble() * 10; //[0,1.0)
}
