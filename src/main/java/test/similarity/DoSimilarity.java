package test.similarity;

import org.apache.lucene.search.spell.JaroWinklerDistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DoSimilarity {

    private Test t = new Test();
    /**
     *  未匹配出来的地址集合
      */
    private ArrayList<String> addressList = new ArrayList<>();

    /**
     *  1、拿到待匹配数据
     */
    public void getNoProjcodeAddress() {
        try {
            Statement state = t.getConnection().createStatement();
            String sql = " SELECT address FROM no_projcode_address ";
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                String address = rs.getString(1);
                addressList.add(address);
            }
            System.out.println(addressList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2、获得相似度
     */
    public void getSimilarity() {
        JaroWinklerDistance jaro = new JaroWinklerDistance();
        Test t = new Test();
        t.initMap();
        // address, newcode
        Map<String, String> map = Test.addressMap;

        for (String address : addressList) {
            // 遍历50w已经找到newcode的地址获取相似度最高的5个地址，按相似度排序
            Map<Float, String> addressMapperPoint = new HashMap<>(10);
            List<Float> pointList = new ArrayList<>(10);
            Set<Float> pointSet = new HashSet<>(10);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String hasNewCodeAddress = entry.getKey();
                float point = jaro.getDistance(address, hasNewCodeAddress);
//                if (pointList.size() < 5) {
//                    pointList.add(point);
//                }
                if (pointSet.size() < 5) {
                    pointSet.add(point);
                }
//                addressMapperPoint.put(point, hasNewCodeAddress);

                if (addressMapperPoint.size() >= 5) {
                    // 对addressMapperPoint中的得分进行排序
//                    Float minPoint = Collections.min(pointList);
                    Float minPoint = Collections.min(pointSet);
                    if (minPoint < point) {
                        addressMapperPoint.remove(minPoint);
//                        for (int i = 0; i < pointList.size(); i++) {
//                            if (pointList.get(i).equals(minPoint)) {
//                                pointList.remove(i);
//                                pointList.add(point);
//                            }
//                        }
                        if (pointSet.contains(minPoint)) {
                            pointSet.remove(minPoint);
                            pointSet.add(point);
                        }
                        addressMapperPoint.put(point, hasNewCodeAddress);
                    }
                }else {
                    addressMapperPoint.put(point, hasNewCodeAddress);
                }
            }
            String sql = "";

//            for (int i = 0; i < pointList.size(); i++) {
//                sql = " INSERT INTO result_address ( address, nearest_address, point, newcode ) VALUES ('" + address + "', '" + addressMapperPoint.get(pointList.get(i)) + "', '"+ pointList.get(i) + "', '"+ map.get(addressMapperPoint.get(pointList.get(i))) + "')";
//                System.out.println(sql);
//                t.insert(sql);
//            }
            for (Float f : pointSet) {
                sql = " INSERT INTO result_address ( address, nearest_address, point, newcode ) VALUES ('" + address + "', '" + addressMapperPoint.get(f) + "', '"+ f + "', '"+ map.get(addressMapperPoint.get(f)) + "')";
                System.out.println(sql);
                t.insert(sql);
            }
        }
    }

    public Float getMinPoint(List<Float> pointList) {
        Float f = Collections.max(pointList);
        return f;
    }

    public static void main(String[] args) {
        DoSimilarity doSimilarity = new DoSimilarity();
        doSimilarity.getNoProjcodeAddress();
        doSimilarity.getSimilarity();

    }

    @org.junit.Test
    public void testFindMinPoint() {
        DoSimilarity d = new DoSimilarity();
        List<Float> pointList = new ArrayList<>(10);
        pointList.add(3.6f);
        pointList.add(3.315466f);
        pointList.add(4.3546f);
        pointList.add(3.643345f);
        pointList.add(3.66987321f);

        Float f = d.getMinPoint(pointList);
        System.out.println(f);
    }

}
