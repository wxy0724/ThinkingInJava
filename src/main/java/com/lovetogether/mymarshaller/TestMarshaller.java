package com.lovetogether.mymarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;

public class TestMarshaller {

    public static void main(String[] args) {
        marshaller();
    }

    public static void marshaller(){
        try {
            JAXBContext jaxbC = JAXBContext.newInstance(Girl.class);
            Marshaller ms = jaxbC.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Girl girl = new Girl();
            ArrayList<Girl> girls = new ArrayList<>(3);
            Girl g1 = new Girl("小红", "20");
            Girl g2 = new Girl("小芳", "16");
            Girl g3 = new Girl("小丽", "17");
            girls.add(g1);
            girls.add(g2);
            girls.add(g3);
            girl.setGirlList(girls);
            StringWriter sw = new StringWriter();
            ms.marshal(girl, sw);
            System.out.println(sw.toString());
            createFile("E:\\test\\", "test.xml", sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static boolean createFile(String path, String fileName, String fileContent){
        boolean bool = false;
        File file = new File(path + File.separator + fileName);
        try{
            if(!file.exists()){
                file.createNewFile();
                bool = true;
            }
            writeFileContet(path+File.separator + fileName, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public static boolean writeFileContet(String path, String fileContent){
        boolean bool = false;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try{
            File file = new File(path);
            fos = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
            pw = new PrintWriter(writer);
            pw.write(fileContent);
            pw.flush();
            bool = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }
}
