package testapi.interview;

import java.util.ArrayList;

public class Student {

    private String name;
    private ArrayList<String> technology;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTechnology() {
        return technology;
    }

    public void setTechnology(ArrayList<String> technology) {
        this.technology = technology;
    }

    public Student(String name, ArrayList<String> technology) {
        this.name = name;
        this.technology = technology;
    }
}
