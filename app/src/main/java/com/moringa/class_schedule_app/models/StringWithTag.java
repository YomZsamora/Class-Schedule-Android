package com.moringa.class_schedule_app.models;

public class StringWithTag {

    public String name;
    public Integer tag;

    public StringWithTag(String name, Integer tag) {
        this.name = name;
        this.tag = tag;
    }
    @Override
    public String toString() {
        return name;
    }
}
