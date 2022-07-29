package com.project.gone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.project.gone.adapter.RecyclerviewAdapter_hospital;
import com.project.gone.adapter.RecyclerviewAdapter_oxygen_available;

import java.util.ArrayList;

public class oxygen_availability extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerviewAdapter_oxygen_available recyclerviewAdapter_hospital;
    private ArrayList<String> hospitalname;
    private ArrayList<String> hospitaladdress;
    private ArrayList<String> oxygen_available;
    private ArrayList<String> direction;
    private ArrayList<String> contact;
    private ArrayList<String> lat, lon;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen_availability);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(oxygen_availability.this, dashboard.class));
            }
        });


        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer();


        hospitalname = new ArrayList<>();
        hospitaladdress = new ArrayList<>();
        oxygen_available = new ArrayList<String>();
        contact = new ArrayList<>();
        direction = new ArrayList<>();
        lat = new ArrayList<>();
        lon = new ArrayList<>();


        recyclerviewAdapter_hospital = new RecyclerviewAdapter_oxygen_available(hospitalname, hospitaladdress, oxygen_available, this, contact, lat,lon);
        recyclerView = findViewById(R.id.oxygen_available_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerviewAdapter_hospital);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("hospital oxygen data");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                DataSnapshot dataSnapshot = snapshot.child("hospital name");
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    String string = (String) snapshot1.getValue();
                    Log.d("name", "name" + string);
                    hospitalname.add(string);

                }

                DataSnapshot dataSnapshot1 = snapshot.child("hospital address");
                for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()) {
                    String string = (String) snapshot1.getValue();
                    hospitaladdress.add(string);

                }

                DataSnapshot dataSnapshot2 = snapshot.child("oxygen");
                for (DataSnapshot snapshot1 : dataSnapshot2.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    oxygen_available.add(string);
                }

                DataSnapshot dataSnapshot3 = snapshot.child("contact");
                for (DataSnapshot snapshot1 : dataSnapshot3.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    contact.add(string);
                }

                DataSnapshot dataSnapshot4 = snapshot.child("lat");
                for (DataSnapshot snapshot1 : dataSnapshot4.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    lat.add(string);
                }

                DataSnapshot dataSnapshot5 = snapshot.child("lon");
                for (DataSnapshot snapshot1 : dataSnapshot5.getChildren()) {
                    String string = String.valueOf(snapshot1.getValue());
                    lon.add(string);
                }


                container.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                recyclerviewAdapter_hospital.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                startActivity(new Intent(oxygen_availability.this, dashboard.class));

            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(oxygen_availability.this, dashboard.class));
    }
}