package com.moringa.class_schedule_app.firebase;

public class SetData {
    String title,tm,desc;

    public SetData(String title, String tm, String desc) {
        this.title = title;
        this.tm = tm;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
