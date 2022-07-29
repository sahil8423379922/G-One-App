package com.project.gone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.gone.adapter.RecyclerviewAdapter_FAQ;
import com.project.gone.adapter.RecyclerviewAdapter_oxygen_available;

import java.util.ArrayList;

public class frequently_asked_question extends AppCompatActivity {

    private RecyclerviewAdapter_FAQ recyclerviewAdapter_faq;
    private RecyclerView recyclerView;
    ArrayList<String> faq;
    ArrayList<String> link;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequently_asked_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        link = new ArrayList<>();

        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer();

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(frequently_asked_question.this, dashboard.class));
            }
        });

        container.setVisibility(View.VISIBLE);


        faq = new ArrayList<>();

        recyclerviewAdapter_faq = new RecyclerviewAdapter_FAQ(faq,link,this);
        recyclerView = findViewById(R.id.frequently_asked_question);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerviewAdapter_faq);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("faq");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String string = String.valueOf(snapshot1.getKey());
                    faq.add(string);
                    String getlink = String.valueOf(snapshot1.getValue());
                    link.add(getlink);

                }

                recyclerviewAdapter_faq.notifyDataSetChanged();
                container.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                startActivity(new Intent(frequently_asked_question.this, dashboard.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(frequently_asked_question.this,dashboard.class));
    }
}