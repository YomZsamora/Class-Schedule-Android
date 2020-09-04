package com.moringa.class_schedule_app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.adapters.StudentContactListAdapter;
import com.moringa.class_schedule_app.adapters.TMContactListAdapter;
import com.moringa.class_schedule_app.models.StudentModel;
import com.moringa.class_schedule_app.models.TechnicalMentorModel;
import com.moringa.class_schedule_app.services.ClassScheduleApi;
import com.moringa.class_schedule_app.services.ClassScheduleClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentContact extends Fragment {

    @BindView(R.id.studentsContactList)
    RecyclerView mStudentRecyclerView;
    @BindView(R.id.tmContactsList)
    RecyclerView mTMRecyclerView;
    @BindView(R.id.contactProgressBar)
    ProgressBar mStudentProgressBar;
    @BindView(R.id.contactProgressBar2)
    ProgressBar mTMProgressBar;
    @BindView(R.id.studentListError)
    TextView mStudentError;
    @BindView(R.id.tmListError)
    TextView mTMError;
    private List<StudentModel> studentList;
    private List<TechnicalMentorModel> tmList;
    private static StudentContactListAdapter studentAdapter;
    private static TMContactListAdapter tmContactListAdapter;
    private final static String TAG = FragmentContact.class.getSimpleName();

    public FragmentContact() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_contact,container,false);
        ButterKnife.bind(this, view);
        getStudentList();
        getTMList();
        return view;
    }

    //gather list of students from the api
    private void getStudentList() {
        ClassScheduleApi client = ClassScheduleClient.getClient();
        Call<List<StudentModel>> call = client.getStudentsList();
        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                hideProgressBar();
                if(response.isSuccessful()) {
                    studentList = response.body();

                    studentAdapter = new StudentContactListAdapter(studentList, getContext());
                    mStudentRecyclerView.setAdapter(studentAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    mStudentRecyclerView.setLayoutManager(layoutManager);
                    mStudentRecyclerView.setNestedScrollingEnabled(false);
                    showStudentList();
                } else {
                    hideProgressBar();
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }
        });
    }

    private void getTMList() {
        ClassScheduleApi client = ClassScheduleClient.getClient();
        Call<List<TechnicalMentorModel>> call = client.getTechnicalMentorsList();
        call.enqueue(new Callback<List<TechnicalMentorModel>>() {
            @Override
            public void onResponse(Call<List<TechnicalMentorModel>> call, Response<List<TechnicalMentorModel>> response) {
                hideTMProgressBar();
                if(response.isSuccessful()) {
                    tmList = response.body();

                    tmContactListAdapter = new TMContactListAdapter(tmList, getContext());
                    mTMRecyclerView.setAdapter(tmContactListAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    mTMRecyclerView.setLayoutManager(layoutManager);
                    mTMRecyclerView.setNestedScrollingEnabled(false);
                    showTmList();
                } else {
                    hideProgressBar();
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<TechnicalMentorModel>> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
                Log.d(TAG,"on failure", t);
            }
        });
    }

    private void showFailureMessage() {
        mStudentError.setText("Something went wrong. Please check your Internet connection and try again later");
        mStudentError.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mStudentError.setText("Oops, something unexpected happened!");
        mStudentError.setVisibility(View.VISIBLE);
    }

    //these methods change the views' visibility
    private void showStudentList() {
        mStudentRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showTmList() {
        mTMRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mStudentProgressBar.setVisibility(View.GONE);
    }
    private void hideTMProgressBar() {
        mTMProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mStudentProgressBar.setVisibility(View.VISIBLE);
    }
}
