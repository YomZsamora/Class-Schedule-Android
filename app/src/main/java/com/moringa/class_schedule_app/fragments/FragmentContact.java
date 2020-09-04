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
    @BindView(R.id.tmContactsList)
    ListView mTMListView;
    @BindView(R.id.contactProgressBar)
    ProgressBar mStudentProgressBar;
    @BindView(R.id.contactProgressBar2)
    ProgressBar mTMProgressBar;
    @BindView(R.id.studentListError)
    TextView mStudentError;
    @BindView(R.id.tmListError)
    TextView mTMError;
    private List<StudentModel> studentList;
    private static StudentContactListAdapter studentListAdapter;
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

                    studentListAdapter = new StudentContactListAdapter(studentList, getContext());
                    showStudentList();
                    Log.d(TAG,String.valueOf(studentList));
                } else {
                    hideProgressBar();
                    showUnsuccessfulMessage();
                    Log.d(TAG,"on unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
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
        mStudentListView.setVisibility(View.VISIBLE);
    }
    private void showTmList() {
        mTMListView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mStudentProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mStudentProgressBar.setVisibility(View.VISIBLE);
    }
}
