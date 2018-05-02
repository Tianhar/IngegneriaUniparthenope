package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class NewsActivity extends AppCompatActivity {

    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_news);

        //TODO - DA MIGLIORARE

        final WebView webview = findViewById(R.id.newsView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setTextZoom(120);
        webview.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                webview.loadUrl("javascript:(function() { " +
                        "document.getElementById('header').style.display='none'; " +
                        "document.getElementById('sidebar').style.display='none'; " +
                        "document.getElementById('content').style.display='block'; " +
                        "})()");

            }
        });
        webview.loadUrl("http://www.ingegneria.uniparthenope.it/avvisi.php");

        webview.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);


        ImageButton bottoneHome = (ImageButton) findViewById(R.id.buttonHome);
        bottoneHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, MainActivity.class));
            }
        });
    }


}


