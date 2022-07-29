package com.project.gone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.gone.adapter.RecyclerViewAdapter;
import com.project.gone.adapter.RecyclerviewAdapter_sensitive_areas;

import java.util.ArrayList;

public class sensitive_areas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> active_cases;
    private ArrayList<String> fatal_cases;
    private ArrayList<String> locality_name;
    private RecyclerviewAdapter_sensitive_areas recyclerViewAdapter;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensitive_areas);

        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer();

        recyclerView = findViewById(R.id.sensitivecases);
        active_cases = new ArrayList<>();
        fatal_cases = new ArrayList<>();
        locality_name = new ArrayList<>();


        recyclerViewAdapter = new RecyclerviewAdapter_sensitive_areas(active_cases, fatal_cases, locality_name);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("cases");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                DataSnapshot dataSnapshot = snapshot.child("active");
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    Log.d("name", "name" + string);
                    active_cases.add(string);

                }

                DataSnapshot dataSnapshot1 = snapshot.child("fatal");
                for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    fatal_cases.add(string);

                }

                DataSnapshot dataSnapshot2 = snapshot.child("locality");
                for (DataSnapshot snapshot1 : dataSnapshot2.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    locality_name.add(string);
                }


                container.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                recyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                startActivity(new Intent(sensitive_areas.this, dashboard.class));

            }
        });


    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(sensitive_areas.this,dashboard.class));
    }
}