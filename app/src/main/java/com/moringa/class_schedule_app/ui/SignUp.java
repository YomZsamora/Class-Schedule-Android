package com.moringa.class_schedule_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.Fmodel;
import com.moringa.class_schedule_app.popup.Popup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignUp";
    FirebaseDatabase fb;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Fmodel fmodel;
    DatabaseReference databaseReference;
    Button login,validate;
    EditText name, password, email, cohort;
    String vname, vpassword, vemail, vcohort;
    String emailFormat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        //auth listener
        mAuth = FirebaseAuth.getInstance();

        //attach to view ids
        login = findViewById(R.id.kill_account);
        validate=findViewById(R.id.validate_btn);
        name = findViewById(R.id.sign_name);
        password = findViewById(R.id.sign_password);
        email = findViewById(R.id.sign_email);
        cohort = findViewById(R.id.sign_cohort);

        progressDialog = new ProgressDialog(SignUp.this);

        //moves to the login activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
                finish();
            }
        });

        //validate_btn call to validate method ,see below
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
        //fire this method to check auth state when activity is created
        createAuthStateListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //essential for the auth state listener to work
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stop firebase from listening to the auth after activity stops, prevents memory leaks
        //and frees up processor workload
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    //logic to check app's auth state
    private void createAuthStateListener() {
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d(TAG,"onAuthChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(SignUp.this,Main.class));
                }else{
                    Log.d(TAG,"onAuthChanged:signed_out:");
                }
            }
        };
    }

    //create account method but with email and password verification in firebase
    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this, "account created", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    popup("Failed", "could not connect to network");
                }
            }
        });
    }

    //dynamically create popups method
    public void popup(String title, String message) {
        Popup popup = new Popup(title, message);
        popup.show(getSupportFragmentManager(), "popup");
    }

    // onclick method for the sign up button .contains firebase verification;
    public void verify() {
        if (name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty() || cohort.getText().toString().isEmpty()) {
            Toast.makeText(this, "please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            if (!email.getText().toString().trim().matches(emailFormat)) {
                email.setError("invalid email");
                Toast.makeText(this, "enter a valid email", Toast.LENGTH_SHORT).show();
            } else {
                //show loading dialog
                progressDialog.setTitle("Creating account");
                progressDialog.setMessage("creating your account ....");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                //find string values of input fields,the v is for validated
                vname = name.getText().toString();
                vpassword = password.getText().toString();
                vemail = email.getText().toString();
                vcohort = cohort.getText().toString();

                //firebase connection query
                databaseReference = FirebaseDatabase.getInstance().getReference("users");
                fmodel = new Fmodel(vname, vpassword, vemail, vcohort);
                Query query = databaseReference.orderByChild("name").equalTo(vname);

                //sends all data to users table for info
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, "signed in", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        password.setText("");
                        email.setText("");
                        cohort.setText("");
                        databaseReference.child(vname).setValue(fmodel);

                        //move to main.class
                        startActivity(new Intent(SignUp.this,Main.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //if it fails ...
                        popup("error", "a network error occurred , check your connection and try again");
                    }
                });
            }
        }
    }
}
