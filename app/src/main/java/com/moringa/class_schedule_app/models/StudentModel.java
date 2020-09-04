package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class StudentModel {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("track")
    @Expose
    public String track;
    @SerializedName("cohort_id")
    @Expose
    public Integer cohortId;
    @SerializedName("id")
    @Expose
    public Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public StudentModel() {
    }

    /**
     *
     * @param uid
     * @param cohortId
     * @param name
     * @param id
     * @param track
     */
    public StudentModel(String name, String uid, String track, Integer cohortId, Integer id) {
        super();
        this.name = name;
        this.uid = uid;
        this.track = track;
        this.cohortId = cohortId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Integer getCohortId() {
        return cohortId;
    }

    public void setCohortId(Integer cohortId) {
        this.cohortId = cohortId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
