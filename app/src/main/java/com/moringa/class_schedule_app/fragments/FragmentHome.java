package com.moringa.class_schedule_app.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringa.class_schedule_app.Interfaces.SessionsInterface;
import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.ListAdapter;
import com.moringa.class_schedule_app.fireModel.SessionsApiModel;
import com.moringa.class_schedule_app.fireModel.SetData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentHome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_home,container,false);
        ListView mlist=root.findViewById(R.id.mylist);
        final List<SetData> setData=new ArrayList<>();
        ListAdapter listAdapter;
        String baseurl="https://class-schedule-api-moringa.herokuapp.com/";

        setData.add(new SetData("meeting","james","There will be a meeting at 10.45 today for all mc 40 students"));
        setData.add(new SetData("stand up","samora","There will be a meeting at 10.45 today for all mc 40 students"));

//        retrofit builer
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SessionsInterface sessionsInterface=retrofit.create(SessionsInterface.class);

//        getting a response from our api
        Call<List<SessionsApiModel>> call= sessionsInterface.getSessions();
        call.enqueue(new Callback<List<SessionsApiModel>>() {
            @Override
            public void onResponse(Call<List<SessionsApiModel>> call, Response<List<SessionsApiModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "failed"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<SessionsApiModel> posts = response.body();


//                loops through each response table adding data to an array being displayed in the listview
                    for (SessionsApiModel posy : posts){
                        String title=posy.getSession_name();
                        String desc=posy.getDescription();
                        String tm=posy.getStart_time();
                        setData.add(new SetData(title,tm,desc));
                    }

            }

            @Override
            public void onFailure(Call<List<SessionsApiModel>> call, Throwable t) {

            }
        });

        listAdapter=new ListAdapter(getContext(),R.layout.list_item,setData);
        mlist.setAdapter(listAdapter);
        return root;
    }
}
