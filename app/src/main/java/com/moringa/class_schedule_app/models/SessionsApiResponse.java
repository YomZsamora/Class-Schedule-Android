package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.SerializedName;

public class SessionsApiResponse {
    private int id,cohort_id,module_id;
    private String session_name,description,start_time,end_time;

    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public String getText(){
        return text;
    }

    public int getCohort_id() {
        return cohort_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public String getDescription() {
        return description;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }
}
