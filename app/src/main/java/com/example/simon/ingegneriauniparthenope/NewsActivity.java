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

        //http://jsoup.org/cookbook/input/load-document-from-url
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.ingegneria.uniparthenope.it/index.php?page=news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element content = doc.getElementById("content");
        Elements newsc = content.getElementsByClass("news");
        ArrayList<String> NewsList = new ArrayList<String>();
        for (Element news : newsc) {
            NewsList.add(news.text());
        }

*/
    }

}


