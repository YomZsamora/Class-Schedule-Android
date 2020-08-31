package com.moringa.class_schedule_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringa.class_schedule_app.Adapters.MyviewAdapter;
import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.Fmodel;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    View root;
    DatabaseReference db;
    RecyclerView firebase_rec;
    ArrayList<Fmodel> list;
    MyviewAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        list=new ArrayList<Fmodel>();
        firebase_rec = (RecyclerView) findViewById(R.id.firebase_rec);
        firebase_rec.setHasFixedSize(true);
        firebase_rec.setLayoutManager(new LinearLayoutManager(this));
        toolbar = findViewById(R.id.toolbar_search);
        db = FirebaseDatabase.getInstance().getReference("users");
        setSupportActionBar(toolbar);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Fmodel p=dataSnapshot.getValue(Fmodel.class);
                    list.add(p);
                }
                adapter=new MyviewAdapter(Search.this,list);
                firebase_rec.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Search.this, "something went wrong check your connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_up, menu);
        MenuItem item=menu.findItem(R.id.search_up);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}