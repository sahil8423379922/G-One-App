package com.project.gone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.shimmer.ShimmerFrameLayout;

public class load_frequently_asked_question extends AppCompatActivity {
    private String link;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_frequently_asked_question);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        link = bundle.getString("link");
        webView = findViewById(R.id.load);

        LoadUrlWebView(link);




    }

    private void LoadUrlWebView(String link) {
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

            webView.loadUrl(link);
        } catch (Exception e) {


        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(load_frequently_asked_question.this,frequently_asked_question.class));
    }
}
