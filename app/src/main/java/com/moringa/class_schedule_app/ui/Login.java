package com.moringa.class_schedule_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.moringa.class_schedule_app.ui.Home;
import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.Fmodel;
import com.moringa.class_schedule_app.popup.Popup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private static final String TAG="Login";
    FirebaseDatabase fb;
    Fmodel fmodel;
    DatabaseReference databaseReference;
    EditText email, password;
    String vemail, vpass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.kill_login);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        mAuth=FirebaseAuth.getInstance();


        //auth listener
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d(TAG,"onAuthChanged:signed_in:" + user.getUid());
                }else{
                    Log.d(TAG,"onAuthChanged:signed_out:");
                }
            }
        };

        //go back to login activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });
    }
    //if user is logged in
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }
    //if user is logged out
    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    //call to custom popup with custom title and message
    public void popup(String t, String m) {
        Popup popup = new Popup(t, m);
        popup.show(getSupportFragmentManager(), "no tag");
    }

    //called by the onclick function of the log in button
    public void login() {

        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            popup("Error", "make sure all fileds are filled in first");
        } else {
            //get string from input fields
            vemail = email.getText().toString();
            vpass = password.getText().toString();
            //connect to firebase

            // email and password sign in with firebase auth
            mAuth.signInWithEmailAndPassword(vemail,vpass);
            startActivity(new Intent(Login.this, Home.class));

        }

    }
}
