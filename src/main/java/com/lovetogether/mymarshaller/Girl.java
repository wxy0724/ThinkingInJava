package com.lovetogether.mymarshaller;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Girl {
    @XmlAttribute(name = "type")
    private String type;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "age")
    private String age;
    @XmlElement(name = "girl")
    private List<Girl> girlList;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Girl girl : girlList){
            sb.append(girl.toString());
        }
        return sb.toString();
    }

    public Girl(){
        super();
    }

    public Girl(String name, String age){
        this.name = name;
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Girl> getGirlList() {
        return girlList;
    }

    public void setGirlList(List<Girl> girlList) {
        this.girlList = girlList;
    }
}
