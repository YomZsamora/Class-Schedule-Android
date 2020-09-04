package com.moringa.class_schedule_app.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class SessionsApiResponse {

    @SerializedName("sessions")
    @Expose
    public List<SessionsModel> sessions = null;

    public SessionsApiResponse() {
    }

    public SessionsApiResponse(List<SessionsModel> sessions) {
        this.sessions = sessions;
    }

    public List<SessionsModel> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionsModel> sessions) {
        this.sessions = sessions;
    }
}
