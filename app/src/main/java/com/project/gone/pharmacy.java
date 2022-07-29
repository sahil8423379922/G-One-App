package com.project.gone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.shimmer.ShimmerFrameLayout;

public class pharmacy extends AppCompatActivity {
    private WebView webView;
    private String url="https://pharmeasy.in/?isSEM=true&&utm_source=google&utm_medium=cpc&utm_campaign=[GSB_New_cx_FP]&utm_term=pharmeasy&utm_adgroup=[Core]&placement_id_identifier=&ad_group_id_identifier=119152222005&device=m&location=9302009&gclid=Cj0KCQjw5uWGBhCTARIsAL70sLLmwVYsN-xN2kv96OABqXjmV3X68gIRlHGO1jVqudIL9MsnInbSJGEaApw0EALw_wcB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);

        webView = findViewById(R.id.pharmacy);

        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer();

        container.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);
        try {
            webView.setWebViewClient(new WebViewClient());

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);

            webView.setWebChromeClient(new WebChromeClient() {

                public void onProgressChanged(WebView view, int progress) {

                    if (progress == 100) {
                        container.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }


                }
            });

            webView.loadUrl(url);
        } catch (Exception e) {


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(pharmacy.this,dashboard.class));
    }
}
