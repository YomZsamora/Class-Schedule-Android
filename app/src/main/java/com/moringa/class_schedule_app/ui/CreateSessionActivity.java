package com.moringa.class_schedule_app.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fragments.FragmentDate;
import com.moringa.class_schedule_app.fragments.TimeFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateSessionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
//    com.moringa.class_schedule_app.ui.DatePicker
//    com.moringa.class_schedule_app.ui.TimePicker
    @BindView(R.id.submitButton) Button mSubmitButton;
    @BindView(R.id.editTextSessionName) EditText mEditTextSessionName;
    @BindView(R.id.textViewStartTime) TextView mTextViewStartTime;
    @BindView(R.id.textViewEndTime) TextView mTextViewEndTime;
    @BindView(R.id.textViewDate) TextView mTextViewDate;
    @BindView(R.id.cohort_spinner) Spinner mCohortSpinner;
    @BindView(R.id.editTextModule) EditText mEditTextModule;
    @BindView(R.id.editTextDescription) EditText mEditTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

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
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        mTextViewStartTime.setText(hourOfDay + ":" + minute);
        mTextViewEndTime.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onClick(View view) {
        if (view == mSubmitButton) {
            Intent intent = new Intent(CreateSessionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mTextViewDate) {
            DialogFragment datePicker = new FragmentDate();
            datePicker.show(getSupportFragmentManager(), "date picker");

        }

        if (view == mTextViewStartTime) {
            DialogFragment timePicker = new TimeFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        }

        if (view == mTextViewEndTime) {
            DialogFragment timePicker = new TimeFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
        }

    }
}