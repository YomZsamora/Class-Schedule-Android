package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TechnicalMentorModel {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("id")
    @Expose
    public Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public TechnicalMentorModel() {
    }

    /**
     *
     * @param uid
     * @param name
     * @param id
     */
    public TechnicalMentorModel(String name, String uid, Integer id) {
        super();
        this.name = name;
        this.uid = uid;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
