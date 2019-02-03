package com.lovetogether.myenum;

import java.util.EnumMap;
import java.util.Map;

import static com.lovetogether.myenum.AlarmPoints.*;

interface Command {
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
        em.put(KITCHEN, () -> System.out.println("Kitchen fire!"));

        em.put(BATHROOM, () -> System.out.println("BATHROOM fire!"));

        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            System.out.println(e.getKey() + " : ");
            e.getValue().action();
        }

        try {
            em.get(UTILITY).action();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
