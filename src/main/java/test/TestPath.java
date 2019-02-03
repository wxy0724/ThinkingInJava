package test;

import org.junit.Test;

public class TestPath {

    @Test
    public void getPath() {
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("/"));
    }

}
