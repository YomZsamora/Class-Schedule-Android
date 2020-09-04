package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class CohortsApiResponse {
    @SerializedName("students")
    @Expose
    public ArrayList<CohortModel> cohorts = null;

    public CohortsApiResponse() {
    }

    public CohortsApiResponse(ArrayList<CohortModel> cohorts) {
        this.cohorts = cohorts;
    }

    public ArrayList<CohortModel> getCohorts() {
        return cohorts;
    }

    public void setCohorts(ArrayList<CohortModel> cohorts) {
        this.cohorts = cohorts;
    }
}
