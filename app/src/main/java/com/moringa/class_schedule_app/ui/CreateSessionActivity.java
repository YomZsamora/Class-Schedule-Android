package com.moringa.class_schedule_app.ui;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fragments.FragmentDate;
import com.moringa.class_schedule_app.fragments.TimeFragment;
import com.moringa.class_schedule_app.models.SessionsModel;
import com.moringa.class_schedule_app.services.ClassScheduleApi;
import com.moringa.class_schedule_app.services.ClassScheduleClient;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSessionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {
    public static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.submitButton) Button mSubmitButton;
    @BindView(R.id.editTextSessionName) EditText mEditTextSessionName;
    @BindView(R.id.textViewStartTime) TextView mTextViewStartTime;
    @BindView(R.id.textViewEndTime) TextView mTextViewEndTime;
    @BindView(R.id.textViewDate) TextView mTextViewDate;
    @BindView(R.id.cohort_spinner) Spinner mCohortSpinner;
    @BindView(R.id.editTextModule) EditText mEditTextModule;
    @BindView(R.id.editTextDescription) EditText mEditTextDescription;

    private Calendar start_time;
    private Calendar end_time;

    private TextView activeDisplay;
    private Calendar activeTime;
    TimePickerDialog.OnTimeSetListener startTimeListener, endTimeListener;
    static final int TIME_DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        //we initialize both times
        start_time = Calendar.getInstance();
        end_time = Calendar.getInstance();

        Spinner spinner = findViewById(R.id.cohort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cohorts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((adapter));
        spinner.setOnItemSelectedListener(this);

        ButterKnife.bind(this);
        mSubmitButton.setOnClickListener((View.OnClickListener) this);
        mEditTextSessionName.setOnClickListener((View.OnClickListener) this);
        mTextViewStartTime.setOnClickListener((View.OnClickListener)this);
        mTextViewEndTime.setOnClickListener((View.OnClickListener) this);
        mEditTextModule.setOnClickListener((View.OnClickListener) this);
        mEditTextDescription.setOnClickListener((View.OnClickListener) this);
        mTextViewDate.setOnClickListener((View.OnClickListener)this);

        updateDisplay(mTextViewStartTime, start_time);
        updateDisplay(mTextViewEndTime, end_time);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = dayOfMonth + "/" + month + "/" + year;
        mTextViewDate.setText(currentDateString);

    }

    @Override
    public void onClick(View view) {
        if (view == mSubmitButton) {
            Intent intent = new Intent(CreateSessionActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mTextViewDate) {
            DialogFragment datePicker = new FragmentDate();
            datePicker.show(getSupportFragmentManager(), "date picker");
        }

        if (view == mTextViewStartTime) {
            showTimeDialog(mTextViewStartTime, start_time);

        }

        if (view == mTextViewEndTime) {
            showTimeDialog(mTextViewEndTime, end_time);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeSetListener, activeTime.get(Calendar.HOUR_OF_DAY), activeTime.get(Calendar.MINUTE), DateFormat.is24HourFormat(this));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(activeTime.get(Calendar.HOUR_OF_DAY),activeTime.get(Calendar.MINUTE));
                break;
        }
    }

    private void updateDisplay(TextView dateDisplay, Calendar time) {
        dateDisplay.setText(String.format("%s:%s", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE)));
    }

    private void showTimeDialog(TextView timeDisplay, Calendar time) {
        activeDisplay = timeDisplay;
        activeTime = time;
        showDialog(TIME_DIALOG_ID);
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            activeTime.set(Calendar.HOUR_OF_DAY, hour);
            activeTime.set(Calendar.MINUTE, minute);
            updateDisplay(activeDisplay, activeTime);
            unregisterTimeDisplay();
        }
    };

    private void unregisterTimeDisplay() {
        activeDisplay = null;
        activeTime = null;
    }

    private void createNewSession() {
        ClassScheduleApi client = ClassScheduleClient.getClient();
        String startTime = mTextViewStartTime.getText().toString().trim();
        Timestamp startTimeTimestamp = Timestamp.valueOf(startTime);
        String sessionName = mEditTextSessionName.getText().toString().trim();
        SessionsModel newSession = new SessionsModel(sessionName, de);
        Call<SessionsModel> call = client.createNewSession(newSession);
        call.enqueue(new Callback<List<SessionsModel>>() {
            @Override
            public void onResponse(Call<List<SessionsModel>> call, Response<List<SessionsModel>> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    Log.d(TAG, String.valueOf(mSessionsList));
                    //toggle the recyclerview visibility
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

    }

    private void showUnsuccessfulMessage() {

    }

    private void hideProgressBar() {

    }

}