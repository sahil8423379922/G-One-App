package com.project.gone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Locale;

public class Direction extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String lat = bundle.getString("lat");
        String lon = bundle.getString("lon");

        String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lon+ " (" + "M.Kashiram Hospital" + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        this.startActivity(intent);


    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(Direction.this, dashboard.class));
    }
}