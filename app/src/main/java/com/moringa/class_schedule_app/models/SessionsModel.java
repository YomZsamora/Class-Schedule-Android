package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.sql.Timestamp;
import java.util.Date;

@Parcel
public class SessionsModel {
    @SerializedName("session_name")
    @Expose
    public String sessionName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("cohort_id")
    @Expose
    public Integer cohortId;
    @SerializedName("module_id")
    @Expose
    public Integer moduleId;
    @SerializedName("start_time")
    @Expose
    public Timestamp startTime;
    @SerializedName("end_time")
    @Expose
    public Timestamp endTime;
    @SerializedName("id")
    @Expose
    public Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public SessionsModel() {
    }

    /**
     *
     * @param cohortId
     * @param sessionName
     * @param description
     * @param startTime
     * @param endTime
     * @param moduleId
     */
    public SessionsModel(String sessionName, String description, Integer cohortId, Integer moduleId, Timestamp startTime, Timestamp endTime) {
        super();
        this.sessionName = sessionName;
        this.description = description;
        this.cohortId = cohortId;
        this.moduleId = moduleId;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCohortId() {
        return cohortId;
    }

    public void setCohortId(Integer cohortId) {
        this.cohortId = cohortId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

}
