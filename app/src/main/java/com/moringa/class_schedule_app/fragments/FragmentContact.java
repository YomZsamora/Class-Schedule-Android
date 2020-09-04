package com.moringa.class_schedule_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.adapters.StudentContactListAdapter;
import com.moringa.class_schedule_app.models.StudentModel;
import com.moringa.class_schedule_app.services.ClassScheduleApi;
import com.moringa.class_schedule_app.services.ClassScheduleClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentContact extends Fragment {

    @BindView(R.id.studentsContactList)
    ListView mStudentListView;
    private List<StudentModel> studentList;
    private static StudentContactListAdapter studentListAdapter;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_contact,container,false);
        ButterKnife.bind(this, view);
        getStudentList();
        return view;
    }

    //gather list of students from the api
    private void getStudentList() {
        ClassScheduleApi client = ClassScheduleClient.getClient();
        Call<List<StudentModel>> call = client.getStudentsList();
        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                if(response.isSuccessful()) {
                    studentList = response.body();

                    studentListAdapter = new StudentContactListAdapter(studentList, getContext());
                    mStudentListView.setAdapter(studentListAdapter);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {

            }
        });
    }
}
