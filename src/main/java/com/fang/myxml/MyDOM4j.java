package com.fang.myxml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 特征：
 　　　　1、JDOM的一种智能分支，它合并了许多超出基本XML文档表示的功能。
 　　　　2、它使用接口和抽象基本类方法。
 　　　　3、具有性能优异、灵活性好、功能强大和极端易用的特点。
 　　　　4、是一个开放源码的文件
 */
public class MyDOM4j {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    /**
     * @param args
     */
    public static void main(String[] args) {
        //解析books.xml
        //创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try{
            //通过reader对象的read方法加载books.xml文件，获取document对象
            Document document = reader.read(new File("books.xml"));
            //通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            //通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            //遍历迭代器，获取根节点中的信息(书籍)
            while(it.hasNext()){
                System.out.println("=====开始遍历谋一本书=====");
                Element book = (Element)it.next();
                //获取book的属性名及属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while(itt.hasNext()){
                    Element bookChild = (Element)itt.next();
                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                }
                System.out.println("=====遍历结束某一本书=====");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
