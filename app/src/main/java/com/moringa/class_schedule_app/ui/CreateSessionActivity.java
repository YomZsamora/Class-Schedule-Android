package com.moringa.class_schedule_app.ui;


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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fragments.FragmentDate;
import com.moringa.class_schedule_app.models.SessionsModel;
import com.moringa.class_schedule_app.models.StringWithTag;
import com.moringa.class_schedule_app.services.ClassScheduleApi;
import com.moringa.class_schedule_app.services.ClassScheduleClient;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    @BindView(R.id.editTextModule) Spinner mModuleSpinner;
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
        ButterKnife.bind(this);

        //we initialize both times
        start_time = Calendar.getInstance();
        end_time = Calendar.getInstance();

        //cohort spinner
        populateCohortSpinner();
        mCohortSpinner.setOnItemSelectedListener(this);
        //module spinner
        populateModuleSpinner();
        mModuleSpinner.setOnItemSelectedListener(this);

        mSubmitButton.setOnClickListener((View.OnClickListener) this);
        mEditTextSessionName.setOnClickListener((View.OnClickListener) this);
        mTextViewStartTime.setOnClickListener((View.OnClickListener)this);
        mTextViewEndTime.setOnClickListener((View.OnClickListener) this);
        mEditTextDescription.setOnClickListener((View.OnClickListener) this);
        mTextViewDate.setOnClickListener((View.OnClickListener)this);

        updateDisplay(mTextViewStartTime, start_time);
        updateDisplay(mTextViewEndTime, end_time);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        StringWithTag s = (StringWithTag) parent.getItemAtPosition(position);
        Object tag = s.tag;
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
        String currentDateString = year + "-" + month + "-" + dayOfMonth;
        mTextViewDate.setText(currentDateString);

    }

    @Override
    public void onClick(View view) {
        if (view == mSubmitButton) {
            try {
                createNewSession();
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        dateDisplay.setText(String.format("%s:%s:00.00", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE)));
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

    private void createNewSession() throws ParseException {
        // valueOf() method returns a Timestamp value corresponding to the given string
        String date= mTextViewDate.getText().toString().trim(); //"2020-08-27";  //our custom time from date picker?
        String start_time=mTextViewStartTime.getText().toString().trim(); //"16:01:15";  //our custom time from date picker?
        String end_time= mTextViewEndTime.getText().toString().trim(); //"16:01:15";
        String start_time_string = String.format("%s %s", date, start_time);
        String end_time_string = String.format("%s %s", date, end_time);
        //our custom time from date picker?
//        Timestamp start_time_ts = Timestamp.valueOf(start_time_string);
//        Timestamp end_time_ts = Timestamp.valueOf(end_time_string);

        //convert our timestamp to the required format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp testStartTime = new Timestamp(sdf.parse(start_time_string).getTime());
        Timestamp testEndTime = new Timestamp(sdf.parse(end_time_string).getTime());
//        Timestamp testStartTime = Timestamp.valueOf(sdf.parse(start_time_string).toString());
        //Timestamp testEndTime = Timestamp.valueOf(sdf.parse(end_time_string).toString());

        //getting the int tag from the selected the spinner item
        Integer cohortId = mCohortSpinner.getSelectedItemPosition() + 1; //spinner lists use indices [0,1,...,n] so we add one to get position
        Integer moduleId = mModuleSpinner.getSelectedItemPosition() +1;

        String sessionName = mEditTextSessionName.getText().toString().trim();
        String description = mEditTextDescription.getText().toString().trim();

        SessionsModel newSession = new SessionsModel(sessionName, description, cohortId, moduleId, testStartTime, testEndTime);
        ClassScheduleApi client = ClassScheduleClient.getClient();
        Call<SessionsModel> call = client.createNewSession(newSession);
        call.enqueue(new Callback<SessionsModel>() {

            @Override
            public void onResponse(Call<SessionsModel> call, Response<SessionsModel> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    Toast.makeText(CreateSessionActivity.this, "Session created successfully", Toast.LENGTH_SHORT).show();
                    SessionsModel debugSession = response.body();
                    Log.d(TAG, String.format("Cohort id : %s", cohortId));
                }

                if (response.code() == 401) {
                    Toast.makeText(CreateSessionActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SessionsModel> call, Throwable t) {
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

    public void populateCohortSpinner() {
        //initialize the spinner with cohort list
        List<StringWithTag> cohortList = new ArrayList<>();
        cohortList.add(new StringWithTag("MC30", 1));
        cohortList.add(new StringWithTag("MC29", 2));
        cohortList.add(new StringWithTag("MC28", 3));
        cohortList.add(new StringWithTag("MC27", 4));
        ArrayAdapter<StringWithTag> cohortAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, cohortList);

        // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cohorts, android.R.layout.simple_spinner_item);
        cohortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCohortSpinner.setAdapter((cohortAdapter));
    }

    public void populateModuleSpinner() {
        List<StringWithTag> moduleList = new ArrayList<>();
        moduleList.add(new StringWithTag("Angular", 1));
        moduleList.add(new StringWithTag("Android", 2));
        moduleList.add(new StringWithTag("Full Stack", 3));
        ArrayAdapter<StringWithTag> moduleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moduleList);
        moduleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mModuleSpinner.setAdapter(moduleAdapter);
    }

}