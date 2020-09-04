package com.moringa.class_schedule_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringa.class_schedule_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.SignUpButton)
    Button mSignUpButton;
    @BindView(R.id.LoginButton) Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ButterKnife.bind(this);
        mSignUpButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mSignUpButton) {
            Intent intent = new Intent(IntroActivity.this, AccountTypeActivity.class);
            startActivity(intent);
            finish();
        }

        if (view == mLoginButton) {
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}