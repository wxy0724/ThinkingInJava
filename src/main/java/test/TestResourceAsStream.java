package test;

import org.junit.Test;

import java.io.InputStream;

public class TestResourceAsStream {

    @Test
    public void testResourceAsStream() {
        // 相对路径
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/picture/pic.png");
        // 绝对路径, 默认从classpath中找文件(文件放在resources目录下)，name不能带“/”，否则会抛空指针
        InputStream resourceAsStream1 = TestResourceAsStream.class.getClassLoader().getResourceAsStream("picture/pic.png");

        System.out.println("ClassName: " + TestResourceAsStream.class.getName());
        System.out.println("ClassSimpleName: " + TestResourceAsStream.class.getSimpleName());
        System.out.println("getPath: " + this.getClass().getResource("/picture/pic.png").getPath());
        System.out.println("");
    }

}
