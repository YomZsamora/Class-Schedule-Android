package com.moringa.class_schedule_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moringa.class_schedule_app.R;
//addonCreateOptionsMenu
public class MainActivity extends AppCompatActivity {
    private int splashTime=3000;
    Animation top_anim,bottom_anim;
    TextView top,bottom;
    RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        r=findViewById(R.id.splash_image);
        top=findViewById(R.id.splash_top_text);
        bottom=findViewById(R.id.splash_bottom_text);
        top_anim= AnimationUtils.loadAnimation(this,R.anim.splash_image);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.splash_text);

        r.setAnimation(top_anim);
        top.setAnimation(bottom_anim);
        bottom.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i=new Intent(MainActivity.this,SignUp.class);
//                startActivity(i);
                finish();
            }
        },splashTime);
    }
}