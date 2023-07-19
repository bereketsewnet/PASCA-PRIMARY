package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoogleTransletorActivity extends AppCompatActivity {

    WebView googletransletorwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_transletor);

        googletransletorwebview = findViewById(R.id.googletransletorwebview);

        // browsring
        googletransletorwebview.setWebViewClient(new WebViewClient());
        //url
        googletransletorwebview.loadUrl("https://translate.google.com");

        WebSettings webSettings = googletransletorwebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(googletransletorwebview.canGoBack()){
            googletransletorwebview.goBack();
        }else{
            super.onBackPressed();
        }

    }
}