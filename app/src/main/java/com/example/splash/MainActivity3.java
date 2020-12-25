
package com.example.splash;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity3 extends AppCompatActivity {



    ProgressBar progressBar;
    Toolbar toolbar;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar =  (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar =  (ProgressBar) findViewById(R.id.progress);
        webView = findViewById(R.id.detail_view);
        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(MainActivity3.this,"Page load Started",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity3.this,"Page load finished",Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(getIntent().getStringExtra("url"));


    }
}