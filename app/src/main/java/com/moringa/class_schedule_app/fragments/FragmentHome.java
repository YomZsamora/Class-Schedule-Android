package com.moringa.class_schedule_app.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    @BindView(R.id.homeErrorText)
    TextView mErrorText;

    private List<SessionsModel> mSessionsList;
    private SessionsListAdapter mAdapter;
    private final static String TAG = FragmentHome.class.getSimpleName();

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
        getSessionsList();
        return view;
    }

    //gather sessions list from api
    private void getSessionsList() {
        ClassScheduleApi client = ClassScheduleClient.getClient();
        Call<List<SessionsModel>> call = client.getSessionList();
        call.enqueue(new Callback<List<SessionsModel>>() {
            @Override
            public void onResponse(Call<List<SessionsModel>> call, Response<List<SessionsModel>> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    mSessionsList = response.body();

                    mAdapter = new SessionsListAdapter(mSessionsList, getContext());
                    mSessionsRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    mSessionsRecyclerView.setLayoutManager(layoutManager);
                    mSessionsRecyclerView.setHasFixedSize(true);

                    Log.d(TAG, String.valueOf(mSessionsList));
                    //toggle the recyclerview visibility
                    showSessionsList();
                } else {
                    hideProgressBar();
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<SessionsModel>> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
                Log.d(TAG, "on failure", t);
            }
        });
    }

    private void showFailureMessage() {
        mErrorText.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorText.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorText.setText("Oops, something unexpected happened!");
        mErrorText.setVisibility(View.VISIBLE);
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
