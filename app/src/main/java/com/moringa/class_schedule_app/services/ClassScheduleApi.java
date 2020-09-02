package com.moringa.class_schedule_app.services;

import com.moringa.class_schedule_app.models.SessionsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClassScheduleApi {
    @GET("sessions")
    Call<SessionsApiResponse> getSessionList();


   @POST("student/new")
    Call<StudentModel> createStudent();

    @POST("mentor/new")
    Call<TechnicalMentorModel> createTechnicalMentor();
}


