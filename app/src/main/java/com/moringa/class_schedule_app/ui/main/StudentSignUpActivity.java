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
import android.widget.TextView;
import android.widget.Toast;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, StudentSignUpActivityInterface {
    @BindView(R.id.createAccountButton) Button mCreateAccountButton;
    @BindView(R.id.logTextView) TextView mLogTextView;
    @BindView(R.id.editTextName) EditText mEditTextName;
    @BindView(R.id.editTextName) EditText mEditTextEmail;
    @BindView(R.id.cohort_spinner) Spinner mCohortSpinner;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.editTextConfirmPassword) EditText editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        Spinner spinner = findViewById(R.id.cohort_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cohorts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((adapter));
        spinner.setOnItemSelectedListener(this);

        ButterKnife.bind(this);
        mCreateAccountButton.setOnClickListener((View.OnClickListener) this);
        mLogTextView.setOnClickListener((View.OnClickListener) this);
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
        if (view == mLogTextView) {
            Intent intent = new Intent(StudentSignUpActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == mCreateAccountButton) {
            createNewUser();
        }

    }

    private void createNewUser() {

    }
}