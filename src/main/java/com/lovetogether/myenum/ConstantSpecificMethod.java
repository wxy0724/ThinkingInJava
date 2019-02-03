package com.lovetogether.myenum;

import java.text.DateFormat;
import java.util.Date;

public enum ConstantSpecificMethod {
    DATA_TIME {
        String getInfo() {
            return DateFormat.getInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values()) {
            System.out.println(csm.getInfo());
        }
    }
}
