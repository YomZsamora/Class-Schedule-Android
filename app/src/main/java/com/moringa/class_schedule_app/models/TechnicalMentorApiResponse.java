package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class TechnicalMentorApiResponse {

    @SerializedName("students")
    @Expose
    public ArrayList<TechnicalMentorModel> technicalMentors = null;

    public TechnicalMentorApiResponse() {
    }

    public TechnicalMentorApiResponse(ArrayList<TechnicalMentorModel> technicalMentors) {
        this.technicalMentors = technicalMentors;
    }

    public ArrayList<TechnicalMentorModel> getTechnicalMentors() {
        return technicalMentors;
    }

    public void setTechnicalMentors(ArrayList<TechnicalMentorModel> technicalMentors) {
        this.technicalMentors = technicalMentors;
    }

}
