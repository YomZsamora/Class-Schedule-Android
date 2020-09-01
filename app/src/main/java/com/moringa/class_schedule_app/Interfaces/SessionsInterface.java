package com.moringa.class_schedule_app.Interfaces;

import com.moringa.class_schedule_app.fireModel.SessionsApiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SessionsInterface {

    @GET("sessions")
    Call<List<SessionsApiModel>> getSessions();

}
