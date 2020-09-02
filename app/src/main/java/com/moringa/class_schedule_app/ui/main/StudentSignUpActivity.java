package com.moringa.class_schedule_app.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moringa.class_schedule_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, StudentSignUpActivityInterface {
    @BindView(R.id.createAccountButton)
    Button mCreateAccountButton;
    @BindView(R.id.logTextView)
    TextView mLogTextView;

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
        if (view == mCreateAccountButton) {
            Intent intent = new Intent(StudentSignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (view == mLogTextView) {
            Intent intent = new Intent(StudentSignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}