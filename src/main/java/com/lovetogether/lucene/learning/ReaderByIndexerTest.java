package com.lovetogether.lucene.learning;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class ReaderByIndexerTest {

    @Test
    public void test(){
        String path = "D:\\WorkSpace\\fang\\fangsearch-pro-divideAddr\\fangsearch-divideAddr-demp\\src\\main\\resources\\sorl-home\\lp\\data\\index";
        ReaderByIndexerTest.testDocValues(path);
    }

    public static void testDocValues(String indexDir){
        // 读取索引文件
        DirectoryReader reader = null;
        // 如果有多个段需要merge成一个，获取第一个进行测试，本例中仅仅就有一个段
        SortedDocValues str = null;
        NumericDocValues db = null;
        try {
            reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDir)));
            str = DocValues.getSorted(reader.leaves().get(0).reader(), "city");
            // 数值类型
            db = DocValues.getNumeric(reader.leaves().get(0).reader(), "projname");
            // 读取字符串类型的ByteRef然后打印其内容
            System.out.println("id："+str.get(0).utf8ToString());
            // 注意此处，要与类型对应，如果是Float，则需要Float.intBitsToFloat((int)db.get(0))进行位数还原
            System.out.println("price: "+Double.longBitsToDouble(db.get(0)));
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

//  public static void searchTerm(String indexDir){
//    // 得到读取索引文件的路径
//    Directory dir = FSDirectory.open(Paths.get(indexDir));
//
//    // 通过dir得到的路径下的所有的文件
//    IndexReader reader = DirectoryReader.open(dir);
//
//    // 建立索引查询器
//    IndexSearcher searcher = new IndexSearcher(reader);
//
//    Term term = new Term("city", "北京");
//    TermQuery tq = new TermQuery(term);
//    try {
//      TopDocs docs = searcher.search(tq, 10000);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    try {
//      TopDocs search = searcher.search(tq, 100);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//
//  }

    public static void search(String indexDir, String par) throws Exception {

        // 得到读取索引文件的路径
        Directory dir = FSDirectory.open(Paths.get(indexDir));

        // 通过dir得到的路径下的所有的文件
        IndexReader reader = DirectoryReader.open(dir);

        // 建立索引查询器
        IndexSearcher searcher = new IndexSearcher(reader);

        // 中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();

        // 建立查询解析器
        /**
         * 第一个参数是要查询的字段； 第二个参数是分析器Analyzer
         */
        QueryParser parser = new QueryParser("desc", analyzer);

        // 根据传进来的par查找
        Query query = parser.parse(par);

        // 计算索引开始时间
        long start = System.currentTimeMillis();

        // 开始查询
        /**
         * 第一个参数是通过传过来的参数来查找得到的query； 第二个参数是要出查询的行数
         */
        TopDocs topDocs = searcher.search(query, 10);

        // 索引结束时间
        long end = System.currentTimeMillis();

        System.out
                .println("匹配" + par + ",总共花费了" + (end - start) + "毫秒,共查到" + topDocs.totalHits + "条记录。");

        /**
         * // 高亮显示start //********高亮显示start******* //算分 QueryScorer scorer=new QueryScorer(query);
         * //显示得分高的片段 Fragmenter fragmenter=new SimpleSpanFragmenter(scorer); //设置标签内部关键字的颜色
         * //第一个参数：标签的前半部分；第二个参数：标签的后半部分。 SimpleHTMLFormatter simpleHTMLFormatter=new
         * SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
         * //第一个参数是对查到的结果进行实例化；第二个是片段得分（显示得分高的片段，即摘要） Highlighter highlighter=new
         * Highlighter(simpleHTMLFormatter, scorer); //设置片段 highlighter.setTextFragmenter(fragmenter);
         * //******高亮显示end****** String desc = document.get("desc"); if(desc!=null){
         *
         * //把全部得分高的摘要给显示出来 //第一个参数是对哪个参数进行设置；第二个是以流的方式读入 TokenStream
         * tokenStream=analyzer.tokenStream("desc", new StringReader(desc)); //获取最高的片段
         * System.out.println(highlighter.getBestFragment(tokenStream, desc)); } } // 算分 QueryScorer
         * scorer = new QueryScorer(query);
         *
         * // 显示得分高的片段 Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
         *
         * // 设置标签内部关键字的颜色 // 第一个参数：标签的前半部分；第二个参数：标签的后半部分。 SimpleHTMLFormatter simpleHTMLFormatter = new
         * SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
         *
         * // 第一个参数是对查到的结果进行实例化；第二个是片段得分（显示得分高的片段，即摘要） Highlighter highlighter = new
         * Highlighter(simpleHTMLFormatter, scorer);
         *
         * // 设置片段 highlighter.setTextFragmenter(fragmenter);
         *
         * // 高亮显示end

         // 遍历topDocs
         /**
         * ScoreDoc:是代表一个结果的相关度得分与文档编号等信息的对象。 scoreDocs:代表文件的数组
         *
         * @throws Exception
         */
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

            // 获取文档
            Document document = searcher.doc(scoreDoc.doc);

            // 输出全路径
            System.out.println(document.get("city"));
            System.out.println(document.get("desc"));

            String desc = document.get("desc");
            if (desc != null) {

                // 把全部得分高的摘要给显示出来

                // 第一个参数是对哪个参数进行设置；第二个是以流的方式读入
                TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(desc));

                // 获取最高的片段
                // System.out.println(highlighter.getBestFragment(tokenStream, desc));
            }
        }

        reader.close();
    }


    // 开始测试
    public static void main(String[] args) {

        // 索引指定的路径
        String indexDir = "D:\\WorkSpace\\fang\\fangsearch-pro-divideAddr\\fangsearch-divideAddr-demp\\src\\main\\resources\\sorl-home\\lp\\data\\index";


        // 查询的字段
        String par = "南京文明";

        try {

            search(indexDir, par);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
