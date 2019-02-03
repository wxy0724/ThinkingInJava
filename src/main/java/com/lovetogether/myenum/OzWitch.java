package com.lovetogether.myenum;

public enum OzWitch {
    WEST("Miss Gu"),
    EAST("EAST"),
    SOUTH("SOUTH"),
    NORTH("NORTH");

    private String description;

    OzWitch(String description) {
        this.description = description;
    }
}
