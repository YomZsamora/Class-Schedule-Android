package com.moringa.class_schedule_app.services;

import com.moringa.class_schedule_app.models.CohortModel;
import com.moringa.class_schedule_app.models.CohortsApiResponse;
import com.moringa.class_schedule_app.models.ModuleModel;
import com.moringa.class_schedule_app.models.ModulesApiResponse;
import com.moringa.class_schedule_app.models.SessionsApiResponse;
import com.moringa.class_schedule_app.models.SessionsModel;
import com.moringa.class_schedule_app.models.StudentModel;
import com.moringa.class_schedule_app.models.StudentsApiResponse;
import com.moringa.class_schedule_app.models.TechnicalMentorApiResponse;
import com.moringa.class_schedule_app.models.TechnicalMentorModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClassScheduleApi {
    //SESSIONS TABLE
    //get a list of sessions
    @GET("sessions")
    Call<List<SessionsModel>> getSessionList();
    //create new session
    @POST("session/new")
    Call<SessionsModel> createNewSession(
            @Body SessionsModel session
    );

    //STUDENTS
    //create student account
    @POST("student/new")
    Call<StudentModel> createStudent(
            @Body StudentModel student
    );
    //get list of students
    @GET("students")
    Call<StudentsApiResponse> getStudentsList();

    //TECHNICAL MENTORS
    //create technical mentor account
    @POST("mentor/new")
    Call<TechnicalMentorModel> createTechnicalMentor(
            @Body TechnicalMentorModel technicalMentor
    );
    @GET("mentors")
    Call<TechnicalMentorApiResponse> getTechnicalMentorsList();

    //MODULES
    @GET("modules")
    Call<ModulesApiResponse> getModulesList();
    //get individual module by id
    @GET("module/{id}")
    Call<ModuleModel> getModuleById(
            @Path("id") int moduleId
    );

    //COHORTS
    @GET("cohorts")
    Call<CohortsApiResponse> getCohortsList();
    //get individual cohort by id
    @GET("cohort/{id}")
    Call<CohortModel> getCohortById(
            @Path("id") int cohortId
    );
}


