package test;

import org.junit.Test;

import java.time.LocalDateTime;

public class TestLocalDate {

    @Test
    public void test1() {
        System.out.println(LocalDateTime.now().minusYears(11).getYear());
    }

}
