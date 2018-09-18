package com.fang.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QueryTest {

    //索引目录
    String indexDir="E:\\LuceneIndex";
    //测试数据目录
    String dataDir="E:\\LuceneTestData";

    /**
     * Lucence5.5返回IndexWriter实例
     * @param directory
     * @return
     */
    public IndexWriter getIndexWriter(Directory directory){
        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig writerConfig=new IndexWriterConfig(analyzer);
        IndexWriter writer=null;
        try {
            writer =new IndexWriter(directory,writerConfig);
        }catch (Exception e){
            e.printStackTrace();
        }
        return writer;
    }
    public Directory getDirctory(String indexDir){
        Directory directory=null;
        try {
            directory= FSDirectory.open(Paths.get(indexDir));
        }catch (IOException e){
            e.printStackTrace();
        }
        return directory;
    }
    @Test
    public void TestIndexer()throws Exception{
        File folder = new File(dataDir);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File[] files= new File(dataDir).listFiles();
        IndexWriter writer=getIndexWriter(getDirctory(indexDir));
        for(File file : files){
            Document doc=new Document();
            doc.add(new TextField("filePath",file.getCanonicalPath(), Field.Store.YES));
            doc.add(new TextField("context",new FileReader(file)));
            writer.addDocument(doc);
        }
        System.out.println("总共添加了"+writer.numDocs()+"个文档");
        writer.close();
    }
    @Test
    public void testSearcher()throws  Exception{
        IndexReader reader= DirectoryReader.open(getDirctory(indexDir));
        IndexSearcher searcher=new IndexSearcher(reader);
        QueryParser queryParser=new QueryParser("context",new StandardAnalyzer());
        Query queryw=queryParser.parse("Licensor");//完整匹配分词查询
        /**
         * 通配符 ？，*的使用
         */
        Query queryy=queryParser.parse("Lice?sor");//使用？匹配单个字符查询
        Query queryx=queryParser.parse("L*r");//使用*匹配多个字符查询
        /**
         * 布尔运算AND, OR，NOT,+,-的使用,注意：一定要是大写的AND和OR,NOT
         */
        Query queryo=queryParser.parse("Licensor OR ce*");//使用OR联合多关键字查询,也可用空格代替OR
        Query queryoo=queryParser.parse(" Licensor ce*");//这个和使用OR一样的效果
        Query queryjia=queryParser.parse("+Licensor Wildcard");//+代表必须的条件，搜索文档必须包含Licensor 可能有Wildcard
        Query querya=queryParser.parse("Licensor AND ce* AND Licenso?");//使用AND取多个关键字的并集查询
        Query queryNot=queryParser.parse("'Lincensor Apache' NOT 'Apache Licensor'");//搜索Lincensor Apache而不是Apache Licensor
        Query queryjian=queryParser.parse("'Lincensor Apache' - 'Apache Licensor'");//"-"同NOT的效果一样

        /**
         * 使用正则表达式查询
         */
        Query queryRegular=queryParser.parse("/[Lab]icensor/");//这个匹配Lincensor，aicensor，bicensor分词
        Query queryRegularr=queryParser.parse("/[Lab]icenso[a-z]/");//根据需要可以更灵活的使用
        /**
         * 使用~模糊匹配查询
         * 这个要和*号的用法区分下，*号完整通配多个字符查询，而~不是简单的通配，这个模糊匹配和Lucene的评分有关
         */
        Query queryFuzzy=queryParser.parse("icensor~");//可以查到Licensor关键字，而queryParser.parse("icensor*")查不到
        Query queryFuzzyparam=queryParser.parse("Licens~1");//~后面可加0-2的整数来制定模糊匹配度，默认不加为1
        Query queryFuzzyParam=queryParser.parse("Licens cens ~0");//~还可以模糊匹配差异化N字符数的多个关键字
        /**
         * 范围查询,多用于数字和时间的查询
         */
        Query queryRange =queryParser.parse("{abc TO Licens}");//{}abc与Licenszhi间的文件，不包含
        Query queryRangex =queryParser.parse("[abc TO Licens]");//{}abc与Licenszhi间的文件,包含本身
        /**
         * 关键字加权处理查询
         */
        //默认为1，可加权可降权，可通过加权处理给匹配的结果排序
        Query queryBoosting  =queryParser.parse("Licensor Wildcard^4 ");

        /**
         * Grouping组合查询
         */
        Query queryGrouping  =queryParser.parse("(+Licensor  +Wildcard) AND easier");//可使用（）组合多个条件查询

        //ps: 查询部分字符需要转义处理，如（+ - && || ! ( ) { } [ ] ^ " ~ * ? : \ /）

        /**
         * 使用MultiFieldQueryParser进行多个文档域查询
         */
        Map boost=new HashMap();
        boost.put("filePath",1.5F);//设置文档域的权值
        boost.put("context",2F);
        QueryParser multiField=new MultiFieldQueryParser(new String[]{"filePath","context"},new StandardAnalyzer(),boost);
        Query queryq=multiField.parse("lucenetestdata");

        TopDocs topDocs= searcher.search(queryq,10);
        System.out.println("查询结果共有"+topDocs.totalHits+"条");
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            Document document=searcher.doc(scoreDoc.doc);
            System.out.println(document.get("filePath")+"--评分："+scoreDoc.score);
        }
    }

}
