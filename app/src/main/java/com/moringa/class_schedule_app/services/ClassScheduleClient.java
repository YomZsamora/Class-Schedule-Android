package com.moringa.class_schedule_app.services;

import com.moringa.class_schedule_app.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassScheduleClient {

    private static Retrofit retrofit = null;

    public static ClassScheduleApi getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SCHEDULE_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ClassScheduleApi.class);
    }
}
