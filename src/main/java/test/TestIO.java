package test;

import org.junit.Test;

import java.io.File;

public class TestIO {

    @Test
    public void test1() {
        File file = new File("C:\\Users\\User\\Desktop\\图片2.png");
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
    }

}
