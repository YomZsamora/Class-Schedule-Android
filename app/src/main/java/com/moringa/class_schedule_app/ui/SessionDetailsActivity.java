package com.moringa.class_schedule_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.models.SessionsModel;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SessionDetailsActivity extends AppCompatActivity {

    @BindView(R.id.sessionName_Details)
    TextView mSessionName;
    @BindView(R.id.sessionDescriptionTextView_Details)
    TextView mSessionDescription;
    @BindView(R.id.sessionStartTime_Details)
    TextView mSessionStartTime;
    @BindView(R.id.sessionEndTime_Details)
    TextView mSessionEndTime;

    private SessionsModel session;
    private List<SessionsModel> sessionsList;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_details);
        ButterKnife.bind(this);

        sessionsList = Parcels.unwrap(getIntent().getExtras().getParcelable("sessions"));
        position = getIntent().getExtras().getInt("position");
        session = sessionsList.get(position);

        bindSessionDetailsViews();
    }

    private void bindSessionDetailsViews() {

        mSessionName.setText(session.getSessionName());
        mSessionDescription.setText(session.getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm"); //we define the pattern which returns Hour:Minute
        mSessionStartTime.setText(sdf.format(session.getStartTime()));
        mSessionEndTime.setText(sdf.format(session.getEndTime()));

    }
}