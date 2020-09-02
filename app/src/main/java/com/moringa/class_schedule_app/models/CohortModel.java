package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class CohortModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public CohortModel() {
    }

    /**
     *
     * @param name
     * @param id
     * @param startDate
     */
    public CohortModel(String name, String startDate, Integer id) {
        super();
        this.name = name;
        this.startDate = startDate;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
