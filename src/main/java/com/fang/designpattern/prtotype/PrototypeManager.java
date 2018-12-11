package com.fang.designpattern.prtotype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原子模式
 */
public class PrototypeManager {

    private static PrototypeManager pm;
    private Map prototypes;
    public PrototypeManager() {
        prototypes = new HashMap();
    }

    // 使用单例模式来得到原型管理器的唯一实例
    public static PrototypeManager getPrototypeManager() {
        if (pm == null) {
            pm = new PrototypeManager();
        }
        return pm;
    }

    public void register(String name, Object prototype) {
        prototypes.put(name, prototype);
    }

    public void remove (String name) {
        prototypes.remove(name);
    }

    public Prototype getPropotype (String name) {
        Prototype prototype = null;
        if (prototypes.containsKey(name)) {
            try {
                // 将原型中对应的复制品返回给客户
                return (Prototype) ((Prototype) prototypes.get(name)).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                prototype = (Prototype) Class.forName(name).newInstance();
                register(name, prototype);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return prototype;
    }
}
