package com.lovetogether.mytest;

import java.io.*;

public class ReadFile {
    public static void main(String[] args) {
        BufferedWriter out = null;
        try {
            String path = "C:\\Users\\User\\Desktop\\testAddr.txt";//绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File file = new File(path);//读取文件
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            File writeFile = new File("C:\\Users\\User\\Desktop\\testJsonAddr.txt");
            out = new BufferedWriter(new FileWriter(writeFile));
            int i = 1;
            while(line != null){
                out.write("[{\"id\":\"5589\",\"key\":\"" + line + "\"}]\r\n");
                line = br.readLine();//一次读入一行数据
                if (!writeFile.exists()){
                    writeFile.createNewFile();
                }
                System.out.println(Integer.toString(i++));
                out.flush();
            }
//            File writeFile = new File("");
//            writeFile.createNewFile();//创建新文件
//            BufferedWriter out = new BufferedWriter(writeFile);
//            out.write(line+"\r\n");//
//            out.flush();//把缓存区内容压入文件
//            out.close();//关闭流
            System.out.println("GG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
