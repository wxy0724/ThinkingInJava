package com.fang.lucene.demo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.*;

public class Demo3 {

    private static List<Passage> psgList = null;

    // 写对象
    private static IndexWriter writer = null;

    public static void main(String[] args) throws Exception {

        //删除 所有索引
        deleteAll();

        //建立索引
        index();

        //精确查找索引
        search("id",1);

        //清空回收站
        //fourceDelete();

        //精确删除 数据
        delByTerm("id",1);
        search("id",1); //确认id为1 的这一条数据是否存在

        //更新索引
        updateRecord();
        search("id",666);

    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:52:37<br/>
     * 机能概要:获取索引信息库
     */
    public static void getInfo(){
        IndexReader reader =  null;
        try {
            reader = getIndexReader();

            System.out.println("回收站的文章数\t"+reader.numDeletedDocs());
            System.out.println("文档总数（包含删除的文章）"+reader.maxDoc());
            System.out.println("文章数\t"+reader.numDocs());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closerReader(reader);
        }
    }
    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:43:02<br/>
     * 机能概要:更新索引
     */
    public static void updateRecord(){
        IndexWriter writer  = null;
        try {
            // 获取IndexWriter
            writer = getIndexWriter();


            Document doc = new Document();

            // IntField 不能直接检索到，需要结合
            doc.add(new IntField("id",666, Field.Store.YES));

            // 用户String类型的字段的存储，StringField是只索引不分词
            doc.add(new TextField("username", "黄聪", Field.Store.YES));

            // 主要对int类型的字段进行存储，需要注意的是如果需要对InfField进行排序使用SortField.Type.INT来比较，如果进范围查询或过滤，需要采用NumericRangeQuery.newIntRange()
            doc.add(new IntField("age", 25, Field.Store.YES));

            // 对String类型的字段进行存储，TextField和StringField的不同是TextField既索引又分词
            doc.add(new TextField("content", "文章内容", Field.Store.NO));

            doc.add(new StringField("keyword","逗比", Field.Store.YES));

            doc.add(new StringField("email","yellowcong@aliyun.com", Field.Store.YES));

            //更细索引
            writer.updateDocument(new Term("id", "666"), doc);

            //提交数据
            writer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            coloseWriter(writer);
        }
    }

    static {
        psgList = new ArrayList<Passage>();

        // 产生一堆数据
        for (int i = 1; i < 200; i++) {
            psgList.add(new Passage(i, "yellowcong" + UUID.randomUUID().toString().substring(0, i % 5),
                    "717350389@qq.com", "逗比", 23 + new Random().nextInt(i), "三炮",new Date()));
        }
    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午5:37:22<br/>
     * 机能概要:获取索引目录
     *
     * @return 目录
     */
    private static String getIndexPath() {
        // 获取索引的目录
        String path = Demo3.class.getClassLoader().getResource("index").getPath();

        // 不存在就创建目录
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午5:43:57<br/>
     * 机能概要:获取IndexWriter 同一时间 ，只能打开一个 IndexWriter，独占写锁 。内建线程安全机制。
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public static IndexWriter getIndexWriter() throws Exception {
        // 创建IdnexWriter
        String path = getIndexPath();
        FSDirectory fs = FSDirectory.open(FileSystems.getDefault().getPath(path));
        //判断资源是否占用
        if (writer == null || !writer.isLocked(fs)) {
            synchronized (Demo3.class) {
                if (writer == null || !writer.isLocked(fs)) {
                    // 创建writer对象
                    writer = new IndexWriter(fs,
                            new IndexWriterConfig(new StandardAnalyzer()));
                }
            }
        }
        return writer;
    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午5:46:36<br/>
     * 机能概要:获取到IndexReader 任意多个IndexReaders可同时打开，可以跨JVM。
     *
     * @return
     * @throws Exception
     */
    public static IndexReader getIndexReader() throws Exception {
        // 创建IdnexWriter
        String path = getIndexPath();
        FSDirectory fs = FSDirectory.open(FileSystems.getDefault().getPath(path));
        // 获取到读
        return DirectoryReader.open(fs);
    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:28:44<br/>
     * 机能概要:清空回收站的数据
     */
    public static void fourceDelete(){
        IndexWriter writer  = null;
        try {
            // 获取IndexWriter
            writer = getIndexWriter();

            //清空回收站
            writer.forceMergeDeletes();

            //提交事物
            writer.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            coloseWriter(writer);
        }
    }
    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午7:57:04<br/>
     * 机能概要:删除所有的索引
     */
    public static void deleteAll(){
        IndexWriter writer  = null;
        try {
            // 获取IndexWriter
            writer = getIndexWriter();

            // 删除所有的数据
            writer.deleteAll();

            int cnt = writer.numDocs();
            System.out.println("索引条数\t" + cnt);

            //提交事物
            writer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            coloseWriter(writer);
        }
    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:24:16<br/>
     * 机能概要:关闭IndexWriter
     */
    private static void coloseWriter(IndexWriter writer){
        try {
            if(writer != null){
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:10:01<br/>
     * 机能概要: 删除数据
     * @param key 查询范围
     * @param val 值
     */
    public static void delByTerm(String key,Object val) {
        IndexWriter writer =  null;
        try {
            writer =getIndexWriter();

            //精确查询
            Query query = null;

            //定义查询条件
            if(val instanceof  Integer){
                //后面的两个true 表示的是 是否包含 上下的数据
                query =  NumericRangeQuery.newIntRange(key, Integer.parseInt(val.toString()), Integer.parseInt(val.toString()), true, true);
            }else{
                query = new TermQuery(new Term(key, val.toString()));
            }

            //删除某一条数据
            writer.deleteDocuments(query);

            //提交请求
            writer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            coloseWriter(writer);
        }
    }
    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:26:15<br/>
     * 机能概要:关闭IndexReader
     * @param reader
     */
    public static void closerReader(IndexReader reader){
        try {
            if(reader != null){
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午8:10:01<br/>
     * 机能概要: 查询数据
     * @param key 查询范围
     * @param val 值
     */
    public static void search(String key,Object val) {
        IndexReader reader =  null;
        try {
            reader = getIndexReader();

            IndexSearcher searcher = new IndexSearcher(reader);

            //精确查询
            Query query = null;

            //定义查询条件
            if(val instanceof  Integer){
                //后面的两个true 表示的是 是否包含 上下的数据
                query =  NumericRangeQuery.newIntRange(key, Integer.parseInt(val.toString()), Integer.parseInt(val.toString()), true, true);
            }else{
                query = new TermQuery(new Term(key, val.toString()));
            }
            //QueryParser paraser = new QueryParser(Version.LUCENE_45, key, new StandardAnalyzer(Version.LUCENE_45));
            //Query query = paraser.parse(val);

            // 获取查询到的Docuemnt
            TopDocs topDocs = searcher.search(query, 500);
            //总共命中的条数
            System.out.println(topDocs.totalHits);
            for (ScoreDoc score : topDocs.scoreDocs) {
                //
                Document doc = searcher.doc(score.doc);

                //查询到的结果
                String username = doc.get("username");
                System.out.println(username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closerReader(reader);
        }
    }

    /**
     * 创建用户:狂飙的yellowcong<br/>
     * 创建日期:2017年12月2日<br/>
     * 创建时间:下午6:03:33<br/>
     * 机能概要:建立索引
     */
    public static void index() {
        IndexWriter writer  = null;
        try {
            // 1、获取IndexWriter
            writer = getIndexWriter();

            // 2、建立索引
            for (Passage psg : psgList) {
                Document doc = new Document();

                // IntField 不能直接检索到，需要结合
                doc.add(new IntField("id", psg.getId(), Field.Store.YES));

                // 用户String类型的字段的存储，StringField是只索引不分词
                doc.add(new TextField("username", psg.getUsername(), Field.Store.YES));

                // 主要对int类型的字段进行存储，需要注意的是如果需要对InfField进行排序使用SortField.Type.INT来比较，如果进范围查询或过滤，需要采用NumericRangeQuery.newIntRange()
                doc.add(new IntField("age", psg.getAge(), Field.Store.YES));

                // 对String类型的字段进行存储，TextField和StringField的不同是TextField既索引又分词
                doc.add(new TextField("content", psg.getContent(), Field.Store.NO));

                doc.add(new StringField("keyword", psg.getKeyword(), Field.Store.YES));

                doc.add(new StringField("email", psg.getEmail(), Field.Store.YES));

                //日期数据添加索引
                doc.add(new LongField("addDate", psg.getAddDate().getTime(), Field.Store.YES));

                // 3、添加文档
                writer.addDocument(doc);
            }

            // 索引条数
            int cnt = writer.numDocs();
            System.out.println("索引条数\t" + cnt);

            //提交事物
            writer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            coloseWriter(writer);
        }
    }

    static class Passage {
        private int id;
        private String username;
        private String email;
        private String keyword;
        private int age;
        // 这个模拟的是文章
        private String content;
        private Date addDate;

        public Passage(int id, String username, String email, String keyword, int age, String content, Date addDate) {
            super();
            this.id = id;
            this.username = username;
            this.email = email;
            this.keyword = keyword;
            this.age = age;
            this.content = content;
            this.addDate = addDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getAddDate() {
            return addDate;
        }

        public void setAddDate(Date addDate) {
            this.addDate = addDate;
        }
    }

}
