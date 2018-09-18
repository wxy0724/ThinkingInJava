package test.similarity;

import org.apache.lucene.search.spell.JaroWinklerDistance;

import java.util.*;

public class CMBCSimilarity {

    /**
     *  提供招行方法
     */
    public void getMaxSimilarity(String name, String address, String fullAddress) {
        List<String> addressList = new ArrayList<>(3);
        Set<String> addressSet = new HashSet<>(3);
        Set<Float> similaritySet = new HashSet<>(3);
        Map<Float, String> addressMapperPoint = new HashMap<>(10);
        // key = address, value = newcode
        Map<String, String> map = Test.addressMap;

        if (!name.equals("") && name != null) {
            addressSet.add(name);
        }
        if (!address.equals("") && address != null) {
            addressSet.add(address);
        }
        if (!fullAddress.equals("") && fullAddress != null) {
            addressSet.add(fullAddress);
        }

        String mostSimilarAddress = "";
        Float maxSimilarity = 0.0f;

        for (String similarityAddress : addressSet) {
            System.out.println(addressSet.size());
            System.out.println(similarityAddress);

            List<Float> pointList = new ArrayList<>(10);
            Set<Float> pointSet = new HashSet<>(10);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String baseAddress = entry.getKey();
                // 用当前地址去撞已经有newcode的50w地址，得到所有的相似度
                Float similarity = getSimilarity(similarityAddress, baseAddress);

//                mostSimilarAddress = getAddressWhichMostSimilar(addressMapperPoint, pointSet, similarity, baseAddress);
                maxSimilarity = getAddressWhichMostSimilar(addressMapperPoint, pointSet, similarity, baseAddress);
                similaritySet.add(maxSimilarity);

            };
        }
        maxSimilarity = getMaxSimilarity(similaritySet);
        mostSimilarAddress = addressMapperPoint.get(maxSimilarity);
        System.out.println(map.get(mostSimilarAddress));
        System.out.println(mostSimilarAddress);
    }

    public Float getAddressWhichMostSimilar(Map<Float, String> addressMapperPoint, Set<Float> pointSet, Float similarity, String baseAddress) {
        if (pointSet.size() < 5) {
            pointSet.add(similarity);
        }

        if (addressMapperPoint.size() >= 5) {
            Float minPoint = Collections.min(pointSet);
            if (minPoint < similarity) {
                addressMapperPoint.remove(minPoint);
                if (pointSet.contains(minPoint)) {
                    pointSet.remove(minPoint);
                    pointSet.add(similarity);
                }
                addressMapperPoint.put(similarity, baseAddress);
            }
        }else {
            addressMapperPoint.put(similarity, baseAddress);
        }

        Float maxSimilarity = getMaxSimilarity(pointSet);
        if (maxSimilarity > 0.95f) {
//            return addressMapperPoint.get(maxSimilarity);
            return maxSimilarity;
        }
        return 0.0f;
    }

    public Float getSimilarity(String address, String baseAddress) {
        JaroWinklerDistance jaro = new JaroWinklerDistance();
        Float similarity = jaro.getDistance(address, baseAddress);
        return similarity;
    }

    /**
     * 获取最大的相似度
     * @param similarityList
     * @return
     */
    public Float getMaxSimilarity(List<Float> similarityList) {
        return Collections.max(similarityList);
    }

    public Float getMaxSimilarity(Set<Float> pointSet) {
        return Collections.max(pointSet);
    }

    @org.junit.Test
    public void test1() {
        Test t = new Test();
        CMBCSimilarity cmbcSimilarity = new CMBCSimilarity();
        t.initMap();
        cmbcSimilarity.getMaxSimilarity("安宁区十里店街道北滨河西路100号", "金堂县赵镇金堂大道661号2栋3单元1楼2号", "浑南区浑南西路10-2号(红星美凯龙)");
    }

}
