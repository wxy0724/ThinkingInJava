package com.fang.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;

import javax.print.Doc;

public class ReaderByIndexerTest {

    @Test
    public void test(){
      String path = "D:\\WorkSpace\\fang\\fangsearch-pro-divideAddr\\fangsearch-divideAddr-demp\\src\\main\\resources\\sorl-home\\lp\\data\\index";
      ReaderByIndexerTest.testDocValues(path);
    }


    public static void testDocValues(String indexDir){

      DirectoryReader reader = null;
      IndexSearcher searcher = null;
      //如果有多个段需要merge成一个，获取第一个进行测试，本例中仅仅就有一个段
      SortedDocValues str = null;
      SortedDocValues db = null;
      SortedSetDocValues projname = null;
      SortedSetDocValues alias = null;


      try {
        //读取索引文件
        reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDir)));
        searcher = new IndexSearcher(reader);
        TermQuery tq = new TermQuery(new Term("city", "沈阳"));
        TopDocs docs1 = searcher.search(tq, 10);
        TopDocs docs = searcher.search(tq, docs1.totalHits);
          for (int i = 0; i < 10  ; i++) {
              Document doc = searcher.doc(docs.scoreDocs[i].doc);
              System.out.print(docs.scoreDocs[i].doc + "\t");
              System.out.print(doc.get("projname") + "\t");
              System.out.print(doc.get("projcode") + "\t");
              System.out.println(doc.get("city"));
          }
        System.out.println(docs1.totalHits);


        str = DocValues.getSorted(reader.leaves().get(0).reader(), "city");
        System.out.println("city："+str.get(0).utf8ToString());

        projname = DocValues.getSortedSet(reader.leaves().get(0).reader(), "projname");
        System.out.println("projname.getValueCount():" + projname.getValueCount());
          System.out.println("projname: " + projname.lookupOrd(3).utf8ToString());

        alias = DocValues.getSortedSet(reader.leaves().get(0).reader(), "alias01");
          System.out.println(alias.getValueCount());
          BytesRef aliasByte = alias.lookupOrd(3);
          System.out.println("alias: " + aliasByte.utf8ToString());

        BytesRef br = projname.lookupOrd(3);
        System.out.println("br.toString(): " + br.toString());

        String a = new String(br.bytes);
        System.out.println(a);

        int docId = 0;
        for (int i = 0; i < 5; i++) {
            docId = docs.scoreDocs[i].doc;
            System.out.println(docId);
            projname.setDocument(docs.scoreDocs[i].doc);
            long next = projname.nextOrd();
            while(next != -1){
            br = projname.lookupOrd(next);
            System.out.println("docId: " + docs.scoreDocs[i].doc + "  ,  +  projname: " + br.utf8ToString());
            next = projname.nextOrd();
            }
        }

          for (int i = 0; i < 5; i++) {
              docId = docs.scoreDocs[i].doc;
              System.out.println(docId);
              alias.setDocument(docs.scoreDocs[i].doc);
              long next = alias.nextOrd();
              while(next != -1){
                  br = alias.lookupOrd(next);
                  System.out.println("docId: " + docs.scoreDocs[i].doc + "  ,  +  alias: " + br.utf8ToString());
                  next = alias.nextOrd();
              }
          }

          docId = 494;
          System.out.println(docId);
          alias.setDocument(docId);
          long next = alias.nextOrd();
          while(next != -1){
              br = alias.lookupOrd(next);
              System.out.println("docId: " + docId + "  ,  +  alias: " + br.utf8ToString());
              next = alias.nextOrd();
          }

//        for (int i = 0; i < docs.totalHits; i++) {
////            Document doc = searcher.doc(docs.scoreDocs[i].doc);
////            String projname = doc.get("projname");
////            String alias = doc.get("alias");
////            str = DocValues.getSorted(reader.leaves().get(0).reader(), "projname");
//            projname = DocValues.getSortedSet(reader.leaves().get(0).reader(), "projname");
//            System.out.println(projname);
//            long ordinal = projname.nextOrd();
//            System.out.println(ordinal);
//            while(ordinal != -1){
//                BytesRef bf = projname.lookupOrd(ordinal);
//                System.out.println("bf.toString: " + bf.toString());
//                ordinal = projname.nextOrd();
//            }

//            System.out.println("nextOrd: " + projname.nextOrd());
//            System.out.println("getValueCount: " + projname.getValueCount());
//            BytesRef bf = new BytesRef("怡美家园".getBytes());
//            System.out.println("lookupTerm: " + projname.lookupTerm(bf));
//            System.out.println(projname.get(0).);
//        }

//          str = DocValues.getSorted(reader.leaves().get(0).reader(), "city");
        //数值类型
//        db = DocValues.getSorted(reader.leaves().get(0).reader(), "projname");
        //读取字符串类型的ByteRef然后打印其内容
//        System.out.println("id："+str.get(0).utf8ToString());
      } catch (IOException e) {
        e.printStackTrace();
      }finally{
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

}
