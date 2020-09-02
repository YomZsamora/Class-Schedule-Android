package com.moringa.class_schedule_app.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.ui.main.SectionsPagerAdapter;
import com.moringa.class_schedule_app.ui.main.StudentSignUpActivity;

public class Main extends AppCompatActivity {
    Toolbar toolbar;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        //get shared preferences
        sp = getApplicationContext().getSharedPreferences("users", MODE_PRIVATE);
        String nameStr = sp.getString("sharedName", "");
        String emailStr = sp.getString("sharedEmail", "");

        if (!nameStr.equals("") || !nameStr.isEmpty()) {
            toolbar.setTitle(nameStr);
        }

        //floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this,Post.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    //adds the menu items to our appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        return true;
    }
    //switch statement for onclick menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search_menu:
                Intent i = new Intent(Main.this, Search.class);
                startActivity(i);
                break;
            case R.id.logout_menu:
                Toast.makeText(this, "logging out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main.this, StudentSignUpActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}