package com.project.gone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class doctor_consultation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_consultation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctor_consultation.this,dashboard.class));
            }
        });


        YouTubePlayerView youTubePlayerView = findViewById(R.id.introduction_video);
        getLifecycle().addObserver(youTubePlayerView);

        YouTubePlayerView youTubePlayerView1 = findViewById(R.id.introduction_video1);
        getLifecycle().addObserver(youTubePlayerView1);

        YouTubePlayerView youTubePlayerView2 = findViewById(R.id.introduction_video2);
        getLifecycle().addObserver(youTubePlayerView2);


    }
}