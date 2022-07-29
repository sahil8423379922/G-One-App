package com.project.gone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

public class vaccination_screen extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_screen);

        webView = findViewById(R.id.vacination_web);
        LoadUrlWebView("https://selfregistration.cowin.gov.in");


    }

    private void LoadUrlWebView(String url_api) {


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

            webView.loadUrl(url_api);
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
        startActivity(new Intent(vaccination_screen.this,dashboard.class));
    }
}