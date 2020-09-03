package com.moringa.class_schedule_app.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moringa.class_schedule_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountTypeActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.studentButton)
    Button mStudentButton;
    @BindView(R.id.mentorButton) Button mMentorButton;
    @BindView(R.id.logTextView)
    TextView mLogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        ButterKnife.bind(this);
        mStudentButton.setOnClickListener(this);
        mMentorButton.setOnClickListener(this);
        mLogTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mStudentButton) {
            Intent intent = new Intent(AccountTypeActivity.this, StudentSignUpActivity.class);
            startActivity(intent);
            finish();
        }

        if (view == mMentorButton) {
            Intent intent = new Intent(AccountTypeActivity.this, TmSignUpActivity.class);
            startActivity(intent);
            finish();
        }

        if (view == mLogTextView) {
            Intent intent = new Intent(AccountTypeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}