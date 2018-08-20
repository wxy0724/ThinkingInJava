package testapi.interview;

import java.util.*;

/**
 *  如果一项技术只有一个学员选择，则直接为该学员指定该技术；
 	如果一项技术有多个学员选择，则在选择了该项技术的学员中随机指定一位学习该技术；
 	如果一个学员被指定的技术不足两项，则在未被指定的技术中随机指定一项或两项给该学员，以凑足两项但不能多于两项。
 	每个学员被指定的技术不能重复；
 	要求适当的添加注释；
 	需要输出最终的技术指定清单；
 	需要输出未被指定给学员的技术清单。
 */
public class TestInterview {

    static String[] technologies = {
            "VirtualBox",
            "Vagrant",
            "WebSocket",
            "JSONP",
            "Redis",
            "MongoDB",
            "Cassandra",
            "RabbitMQ",
            "ActiveMQ",
            "Kafka",
            "Lucene",
            "Solr",
            "ElasticSearch",
            "Hadoop",
            "HDFS",
            "HIVE",
            "PIG",
            "Mahout",
            "HBase",
            "Spark",
            "Guava",
            "Protobuf",
            "Avro",
            "Thrift",
            "Motan",
            "Docker",
            "DynamoDB",
            "Scala",
            "Groovy",
            "SpringBoot",
    };
    Map<String, List<Student>> resultMap = new HashMap<>(16);
    ArrayList<Student> studentList = new ArrayList<>(13);
    ArrayList<String> technologiesList = new ArrayList<>(10);

    public TestInterview() {
        initStudent();
    }

    public void initStudent(){
        technologiesList.add("ElasticSearch");
        technologiesList.add("Redis");
        Student stu1 = new Student("吕鹏飞", technologiesList);
        studentList.add(stu1);
        technologiesList.clear();
        technologiesList.add("Redis");
        technologiesList.add("SpringBoot");
        Student stu2 = new Student("丁虎", technologiesList);
        studentList.add(stu2);
        technologiesList.clear();
        technologiesList.add("Hadoop");
        technologiesList.add("HDFS");
        Student stu3 = new Student("梁秀斗", technologiesList);
        studentList.add(stu3);
        technologiesList.clear();
        technologiesList.add("Docker");
        technologiesList.add("Kafka");
        Student stu4 =new Student("李文鹏", technologiesList);
        studentList.add(stu4);
        technologiesList.clear();
        technologiesList.add("Lucene");
        technologiesList.add("Solr");
        Student stu5 =new Student("苗桓飞", technologiesList);
        studentList.add(stu5);
        technologiesList.clear();
        technologiesList.add("Solr");
        technologiesList.add("Redis");
        Student stu6 =new Student("佘昊", technologiesList);
        studentList.add(stu6);
        technologiesList.clear();
        technologiesList.add("ActiveMQ");
        technologiesList.add("Hadoop");
        Student stu7 =new Student("杜世阳", technologiesList);
        studentList.add(stu7);
        technologiesList.clear();
        technologiesList.add("SpringBoot");
        technologiesList.add("ActiveMQ");
        Student stu8 =new Student("刘翩", technologiesList);
        studentList.add(stu8);
        technologiesList.clear();
        technologiesList.add("Docker");
        technologiesList.add("Lucene");
        Student stu9 =new Student("史建智", technologiesList);
        studentList.add(stu9);
        technologiesList.clear();
        technologiesList.add("Cassandra");
        technologiesList.add("Spark");
        Student stu10 =new Student("王帅", technologiesList);
        studentList.add(stu10);
        technologiesList.clear();
        technologiesList.add("SpringBoot");
        technologiesList.add("MongoDB");
        Student stu11 =new Student("张昌昌", technologiesList);
        studentList.add(stu11);
        technologiesList.clear();
        technologiesList.add("SpringBoot");
        technologiesList.add("Spark");
        Student stu12 =new Student("王腾飞", technologiesList);
        studentList.add(stu12);
        technologiesList.clear();
        technologiesList.add("WebSocket");
        technologiesList.add("RabbitMQ");
        Student stu13 =new Student("杨小平", technologiesList);
        studentList.add(stu13);
    }

    public static void main(String[] args) {
        TestInterview ts = new TestInterview();
        ArrayList<Student> studentList = ts.studentList;
        Map<String, List<Student>> resultMap = ts.resultMap;
        List<Student> stuLsit = new ArrayList<>(10);
        //如果该技术多个人选，随机选一个放这里
        List<Student> stuLsit001 = new ArrayList<>(10);
        Random random = new Random();

        //遍历所有科目
        for (String technology: technologies) {
            //遍历所有学生
            for (Student student: studentList) {
                //若该学生的选择科目包含当前科目，在添加到结果集合中
                if (student.getTechnology().contains(technology)){
                    stuLsit.add(student);
                }
            }
            while (stuLsit.size() > 2){
                stuLsit001.add(stuLsit.get(random.nextInt(stuLsit.size())));
            }
            resultMap.put(technology, stuLsit);
        }


        Set<Map.Entry<String, List<Student>>> entries = resultMap.entrySet();
        for (Map.Entry<String, List<Student>> entry: entries) {
            String key = entry.getKey();
            List<Student> value = entry.getValue();
            for (Student s: value) {
                System.out.println(key + " : " + s.getName());
            }
        }
    }

}
