package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class StudentsApiResponse {

    @SerializedName("students")
    @Expose
    private ArrayList<StudentModel> students = null;

    public StudentsApiResponse() {
    }

    public StudentsApiResponse(ArrayList<StudentModel> students) {
        this.students = students;
    }

    public ArrayList<StudentModel> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<StudentModel> students) {
        this.students = students;
    }
}
