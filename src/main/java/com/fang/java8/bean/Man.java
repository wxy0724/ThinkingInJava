package com.fang.java8.bean;

public class Man {

    private String name;
    private Integer age;
    private Godness  godness;

    public Man() {}

    public Man(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
