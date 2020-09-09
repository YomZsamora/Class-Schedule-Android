package com.moringa.class_schedule_app.ui;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.moringa.class_schedule_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = ProfileActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    @BindView(R.id.profileUserName)
    TextView mUsername;
    @BindView(R.id.profileEmail)
    TextView mProfileEmail;
    @BindView(R.id.profilePicture)
    ImageView mProfilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               user = firebaseAuth.getCurrentUser();
                if (user != null) {
//                   String name = user.getDisplayName();
//                   String email = user.getEmail();

                    mUsername.setText(user.getDisplayName());
                    mProfileEmail.setText(user.getEmail());

//                boolean emailVerified = user.isEmailVerified();
//
//                String uid = user.getUid();

                } else {

                }
            }

        };
//        displayUserProfile(user);
    }


    private void displayUserProfile(final FirebaseUser user) {
        UserInfo getProfileInfo =  new UserInfo() {
            @NonNull
            @Override
            public String getUid() {
                return user.getUid();
            }

            @NonNull
            @Override
            public String getProviderId() {
                return null;
            }

            @Nullable
            @Override
            public String getDisplayName() {
                return user.getDisplayName();
            }

            @Nullable
            @Override
            public Uri getPhotoUrl() {
                return null;
            }

            @Nullable
            @Override
            public String getEmail() {
                return user.getEmail();
            }

            @Nullable
            @Override
            public String getPhoneNumber() {
                return null;
            }

            @Override
            public boolean isEmailVerified() {
                return false;
            }
        };
//        mAuth.getCurrentUser();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}





