package com.moringa.class_schedule_app.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.adapters.SessionsListAdapter;
import com.moringa.class_schedule_app.models.SessionsApiResponse;
import com.moringa.class_schedule_app.models.SessionsModel;
import com.moringa.class_schedule_app.services.ClassScheduleApi;
import com.moringa.class_schedule_app.services.ClassScheduleClient;

import java.util.List;


public class FragmentHome extends Fragment {

    @BindView(R.id.homeProgressBar)
    ProgressBar mHomeProgressBar;
    @BindView(R.id.sessionsListRecyclerView)
    RecyclerView mSessionsRecyclerView;

    private List<SessionsModel> mSessionsList;
    private SessionsListAdapter mAdapter;

    public FragmentHome() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //gather sessions list from api
    private void getSessionsList() {
        ClassScheduleApi client = ClassScheduleClient.getClient();
        Call<SessionsApiResponse> call = client.getSessionList();
        call.enqueue(new Callback<SessionsApiResponse>() {
            @Override
            public void onResponse(Call<SessionsApiResponse> call, Response<SessionsApiResponse> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    mSessionsList = response.body().getSessions();
                    //getActivity() returns the activity associated with a fragment.
                    //The activity is a context (since Activity extends Context).
                    mAdapter = new SessionsListAdapter(mSessionsList, getActivity());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    mSessionsRecyclerView.setLayoutManager(layoutManager);
                    mSessionsRecyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<SessionsApiResponse> call, Throwable t) {

            }
        });
    }

    //these methods change the views' visibility
    private void showSessionsList() {
        mSessionsRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideSessionsList() {
        mSessionsRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mHomeProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mHomeProgressBar.setVisibility(View.VISIBLE);
    }
}
