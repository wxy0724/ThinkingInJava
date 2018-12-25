package com.lovetogether.lucene.learning;

import org.apache.lucene.store.Directory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.FSDirectory;

public class CreateIndex {
    public CreateIndex() {}

    /**
     * @param DocPath 需要添加到索引中的路径
     * @param IndexPath 存放索引的路径
     * @author User
     *
     */
    public static void CreateIndexPath(String DocPath, String IndexPath) throws Exception {
        Date start = new Date();
        deleteDir(new File(IndexPath));
        try {
            //Directory 这个类代表了Lucene的索引的存储的位置
            //FSDirectory，他表示一个存储在文件系统中的索引的位置
            //Paths.get(IndexPath)获取Path对象
            Directory index_dir = FSDirectory.open(Paths.get(IndexPath));
            //Analyzer:对文档进行分词处理,它有多个实现,把分词后的内容交给IndexWriter来建立索引
            Analyzer analyzer = new StandardAnalyzer();
            //IndexWriterConfig:IndexWriter的配置项，IndexWriter只有一个有参构造器：IndexWriter(Directory, IndexWriterConfig)
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            //创建索引并添加文档到索引中的
            IndexWriter writer = new IndexWriter(index_dir, iwc);
            File docFile = new File(DocPath);
            indexDoc(writer, docFile);

            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");
            writer.close();
        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* 索引添加文档 */
    private static void indexDoc(IndexWriter writer, File file) throws Exception {
        try {
            for (File f : file.listFiles()) {
                Document document = new Document();
                // System.out.println(f.getPath());

                Field FieldPath = new StringField("path", f.getPath(), Field.Store.YES);
                document.add(FieldPath);

                // Field FieldContent = new TextField("content",new FileReader(f));
                //值没存
                Field FieldContent =
                        new TextField("content", "", Field.Store.YES);
                document.add(FieldContent);

                // System.out.println(f.getName());

                Field FieldName = new StringField("name", f.getName(), Field.Store.YES);
                document.add(FieldName);

                writer.addDocument(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除索引
    private static void deleteDir(File dir) {
        try {
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    deleteDir(new File(dir, children[i]));
                }
            }
            dir.delete();
            // System.out.println("delete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

