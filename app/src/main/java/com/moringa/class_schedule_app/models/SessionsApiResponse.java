package com.moringa.class_schedule_app.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class SessionsApiResponse {

    @SerializedName("sessions")
    @Expose
    private ArrayList<SessionsModel> sessions = null;

    public SessionsApiResponse() {
    }

    public SessionsApiResponse(ArrayList<SessionsModel> sessions) {
        this.sessions = sessions;
    }

    public ArrayList<SessionsModel> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<SessionsModel> sessions) {
        this.sessions = sessions;
    }
}
