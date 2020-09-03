package com.moringa.class_schedule_app.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SessionsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SessionsActivityInterface {
    @BindView(R.id.submitButton) Button mSubmitButton;
    @BindView(R.id.editTextSessionName) EditText mEditTextSessionName;
    @BindView(R.id.editTextStartTime) EditText mEditTextStartTime;
    @BindView(R.id.editTextEndTime) EditText mEditTextEndTime;
    @BindView(R.id.editTextDate) EditText mEditTextDate;
    @BindView(R.id.cohort_spinner) Spinner mCohortSpinner;
    @BindView(R.id.editTextModule) EditText mEditTextModule;
    @BindView(R.id.editTextDescription) EditText mEditTextDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        Spinner spinner = findViewById(R.id.cohort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cohorts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((adapter));
        spinner.setOnItemSelectedListener(this);

        ButterKnife.bind(this);
        mSubmitButton.setOnClickListener((View.OnClickListener) this);
        mEditTextSessionName.setOnClickListener((View.OnClickListener) this);
        mEditTextStartTime.setOnClickListener((View.OnClickListener) this);
        mEditTextEndTime.setOnClickListener((View.OnClickListener) this);
        mEditTextDate.setOnClickListener((View.OnClickListener) this);
        mEditTextModule.setOnClickListener((View.OnClickListener) this);
        mEditTextDescription.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view == mSubmitButton) {
            Intent intent = new Intent(SessionsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }
}