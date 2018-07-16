package com.fang.mytest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyCompareTo {

    @Test
    public void testCompare() throws Exception {
        SortUtilForList sortUtilForList = new SortUtilForList();
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1,88));
        students.add(new Student(3,98));
        students.add(new Student(4,88));
        students.add(new Student(2,78));
        students.add(new Student(6,68));
        students.add(new Student(5,88));
        Collections.sort(students, sortUtilForList);
        for (int i = 0; i < students.size(); i++){
            System.out.println(students.get(i).toString());
        }
    }

}

/**
 * 对学生的成绩进行排名，成绩相同的按照学号大小排序
 */
class SortUtilForList implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Student){
            if (((Student) o1).getScore() != ((Student)o2).getScore()){
                // 如果学生的分数不同，按照分数进行排名
                return compareWithScore(((Student) o1).getScore(),((Student)o2).getScore());
            }else {
                // 如果学生的分数相同按照学号进行排名
                return compareWithId(((Student) o1).getId(), ((Student) o2).getId());
            }
        }        return 0;
    }

    private int compareWithId(int id1, int id2) {
        if (id1 > id2){
            return -1;
        }
        return 1;
    }

    private int compareWithScore(int score1, int score2) {
        if (score1 > score2){
            return -1;
        }
        return 1;
    }
}

class Student{
    int id;
    int score;

    public Student() {}

    public Student(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}