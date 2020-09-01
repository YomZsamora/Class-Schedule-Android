package com.moringa.class_schedule_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.Fmodel;
import com.moringa.class_schedule_app.popup.Popup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //firebase auth
    FirebaseDatabase fb;
    DatabaseReference databaseReference;
    //get views
    @BindView(R.id.login_email)
    EditText mEmail;
    @BindView(R.id.login_password)
    EditText mPassword;
    @BindView(R.id.loginbtn)
    Button mLoginButton;
    @BindView(R.id.toSignUpTextView1)
    EditText mSignUpTextView1;
    @BindView(R.id.toSignUpTextView2)
    EditText mSignUpTextView2;

    Fmodel fmodel;
    String vemail, vpass;
    SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    ProgressDialog p;
    Button login;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //attach to ids
//        Button kill_login = findViewById(R.id.kill_login);
//        email = findViewById(R.id.login_email);
//        password = findViewById(R.id.login_password);
        p = new ProgressDialog(Login.this);
        mAuth = FirebaseAuth.getInstance();
//        login = findViewById(R.id.loginbtn);

        //start listening to auth state
        createAuthStateListener();

        //database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }
    //end of onCreate method


    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            login();
        }
        if (view == mSignUpTextView1 || view == mSignUpTextView2){
            Intent intent = new Intent(Login.this, SignUp.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    //firebase auth listener
    public void createAuthStateListener(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //if there exists an already authenticated account
                    Intent intent = new Intent(Login.this, Main.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    //call to custom popup with custom title and message
    public void popup(String t, String m) {
        Popup popup = new Popup(t, m);
        popup.show(getSupportFragmentManager(), "no tag");
    }

    //called by the onclick function of the log in button
//    public void login() {
//        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
//            popup("Error", "make sure all fileds are filled in first");
//        } else {
//            //set loading progress dialog
//            p.setTitle("loading ...");
//            p.setMessage("checking data");
//            p.setCanceledOnTouchOutside(false);
//            p.show();
//
//            //get string from input fields
//            vemail = email.getText().toString();
//            vpass = password.getText().toString();
//
//            //the vemail in this case refers to name not email
//            // email and password sign in with firebase realtime
//            Query query=databaseReference.orderByChild("name").equalTo(vemail);
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()){
//                        //strings from db fields
//                        String edbemail=dataSnapshot.child(vemail).child("email").getValue(String.class);
//                        String edbpassword=dataSnapshot.child(vemail).child("password").getValue(String.class);
//                        String edbname=dataSnapshot.child(vemail).child("name").getValue(String.class);
//                        String edbcohort=dataSnapshot.child(vemail).child("cohort").getValue(String.class);
//
//                        //adding the data to shared prefs
//                        sharedPreferences =getSharedPreferences("users",MODE_PRIVATE);
//                        SharedPreferences.Editor editor= sharedPreferences.edit();
//                        editor.putString("sharedName",edbemail);
//                        editor.putString("sharedEmail",edbemail);
//                        editor.putString("sharedCohort",edbemail);
//
//
//                        if (edbpassword.equals(vpass)){
//                            startActivity(new Intent(Login.this,Main.class));
//                            Toast.makeText(Login.this, "logged in", Toast.LENGTH_SHORT).show();
//                            p.dismiss();
//                            finish();
//                        }else{
//                            popup("ooops....","failed please check your details");
//                            p.dismiss();
//                        }
//                    }else {
//                        popup("Not Found","check the user name");
//                        p.dismiss();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//
//        }
    }
    //firebase login with email and password
    public void firebaseLogin(){
        mAuth.signInWithEmailAndPassword(vemail, vpass)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            p.dismiss();
                            Log.d(TAG, "signInWithEmail:success");
                            startActivity(new Intent(Login.this, Main.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            p.dismiss();
                            popup("ooops...", "something went wrong");
                            startActivity(new Intent(Login.this, Main.class));
                        }
                    }
                });
    }

    //form validation
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailAddress.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }
    private boolean isValidPassword(String password) {
        if (password.equals("")) {
            mPassword.setError("Password cannot be blank");
            return false;
        }
        return true;
    }

}