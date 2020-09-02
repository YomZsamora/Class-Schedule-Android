package com.moringa.class_schedule_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.sql.Timestamp;

@Parcel
public class SessionsModel {
    @SerializedName("session_name")
    @Expose
    private String sessionName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cohort_id")
    @Expose
    private Integer cohortId;
    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("start_time")
    @Expose
    private Timestamp startTime;
    @SerializedName("end_time")
    @Expose
    private Timestamp endTime;
    @SerializedName("id")
    @Expose
    private Integer id;

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
     * @param id
     * @param moduleId
     */
    public SessionsModel(String sessionName, String description, Integer cohortId, Integer moduleId, Timestamp startTime, Timestamp endTime, Integer id) {
        super();
        this.sessionName = sessionName;
        this.description = description;
        this.cohortId = cohortId;
        this.moduleId = moduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
