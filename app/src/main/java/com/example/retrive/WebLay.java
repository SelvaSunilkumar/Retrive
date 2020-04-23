package com.example.retrive;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebLay extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_lay);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progress);
        bundle = getIntent().getExtras();

        progressBar.setVisibility(View.VISIBLE);

        String url = bundle.getString("url");
        String finalUrl = "http://drive.google.com/viewerng/viewer?embedded=true&url=" + url;

        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView webView,int newProgress) {
                super.onProgressChanged(webView, newProgress);

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl(finalUrl);
    }
}
