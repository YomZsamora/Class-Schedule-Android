package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TechnicalMentorApiResponse {

    @SerializedName("students")
    @Expose
    private ArrayList<TechnicalMentorModel> technicalMentors = null;

    public TechnicalMentorApiResponse() {
    }

    public TechnicalMentorApiResponse(ArrayList<TechnicalMentorModel> technicalMentors) {
        this.technicalMentors = technicalMentors;
    }

    public ArrayList<TechnicalMentorModel> getStudents() {
        return technicalMentors;
    }

    public void setStudents(ArrayList<TechnicalMentorModel> technicalMentors) {
        this.technicalMentors = technicalMentors;
    }

}
