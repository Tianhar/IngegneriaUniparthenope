package com.example.simon.ingegneriauniparthenope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.simon.ingegneriauniparthenope.MainActivity.newsd;


public class NewsActivity extends AppCompatActivity {


    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView result = (TextView) findViewById(R.id.result);

        result.setText(newsd.builder.toString());

        //TODO - DA MIGLIORARE
/*
        final WebView webview = findViewById(R.id.newsView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setTextZoom(120);
        webview.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        webview.loadData(newsd.builder.toString(), "text/html; charset=utf-8", "UTF-8");
*/
    }

}


