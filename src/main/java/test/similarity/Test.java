package test.similarity;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static Map<String, String> addressMap = new HashMap<>(500000);

    /**
     *  使用log4j记录日志
     */
    private static Logger logger = Logger.getLogger(Test.class);

    /**
     *  连接驱动
     */
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * 连接路径
     */
    private static final String URL = "jdbc:sqlserver://10.16.193.130:1433;DatabaseName=INDUSTRY_GUJIA_TEST";

    /**
     * 用户名
     */
    private static final String USERNAME = "industry_gujia_test_admin";

    /**
     *  密码
     */
    private static final String PASSWORD = "K9d3b82s";

    //静态代码块
    static {
        try {
            // 加载驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     */
    public Connection getConnection() {
        Connection conn = null;
        logger.debug("开始连接数据库");
        try{
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
            logger.error("数据库连接失败！");
        }
        logger.debug("数据库连接成功");
        return conn;
    }

    /**
     * 关闭数据库连接，注意关闭的顺序
     */
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if(rs!=null){
            try{
                rs.close();
                rs = null;
            }catch(SQLException e){
                e.printStackTrace();
                logger.error("关闭ResultSet失败");
            }
        }
        if(ps!=null){
            try{
                ps.close();
                ps = null;
            }catch(SQLException e){
                e.printStackTrace();
                logger.error("关闭PreparedStatement失败");
            }
        }
        if(conn!=null){
            try{
                conn.close();
                conn=null;
            }catch(SQLException e){
                e.printStackTrace();
                logger.error("关闭Connection失败");
            }
        }
    }

    public void insert(String sql) {
        try {
            Statement state = getConnection().createStatement();
            state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAddress() throws IOException {

        Test t = new Test();
        String path = "C:\\Users\\User\\Desktop\\testAddress.txt";//绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        File file = new File(path);//读取文件
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();

        while(line != null){
//            String[] str = line.split("\t");

            try {
                try (Connection conn = t.getConnection()) {
                    try (Statement state = conn.createStatement()) {
//                        String sql = " INSERT INTO has_projcode_address ( projcode, address ) VALUES ('" + str[0] + "','" + str[1] + "')";
                        String sql = " INSERT INTO no_projcode_address ( address ) VALUES ('" + line + "')";
                        System.out.println(sql);
                        state.executeUpdate(sql);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            line = br.readLine();//一次读入一行数据

        }
    }

    public static void main(String[] args) {
        Test t = new Test();
//        t.initMap();
        try {
            t.insertAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMap() {
        Test t = new Test();
        Connection conn = t.getConnection();
        addressMap = new HashMap<>(500000);

        try {
            Statement state = conn.createStatement();
            String sql = " SELECT DISTINCT(address), projcode FROM has_projcode_address ";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String address = rs.getString(1);
                String newcode = rs.getString(2);
                addressMap.put(address, newcode);
            }

            System.out.println(addressMap.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    //    public static void main(String[] args) {
//        Test t = new Test();
//        Connection conn = t.getConnection();
//        Statement state = null;
//
//        try {
//            state = conn.createStatement();
//            String sql = " SELECT address FROM TABLE ";
//            //查询:
//            ResultSet rs = state.executeQuery(sql);
//            while (rs.next()) {
//
//            }
//
//            //插入: state.executeUpdate(sql)
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}
