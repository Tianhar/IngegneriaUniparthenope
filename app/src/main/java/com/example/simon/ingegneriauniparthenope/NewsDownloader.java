package com.example.simon.ingegneriauniparthenope;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class NewsDownloader extends AsyncTask<Void, Void, Void> {
    public static StringBuilder builder = new StringBuilder();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Document doc = Jsoup.connect("http://www.ingegneria.uniparthenope.it/index.php?page=news").get();
            String title = doc.title();
            Element content = doc.getElementById("content");
            Elements newsc = content.getElementsByClass("news");

            builder.append("News " + title).append("\n\n");

            for (Element link : newsc) {
                Elements dates = link.getElementsByClass("data");
                int darimuovere = dates.text().length();
                Elements titles = link.getElementsByTag("h4");
                Elements contenuto = link.getElementsByTag("p");
                String cont = contenuto.text().replaceAll("’", "'");
                builder.append("\n").append("-News del ").append(dates.text()).append("\n")
                        .append("\n").append(titles.text().substring(darimuovere + 1)).append("\n")
                        .append("\n").append(cont).append("\n").append("\n");
            }
        } catch (IOException e) {
            builder.append("Error : ").append(e.getMessage()).append("\n");
        }
        return null;
    }

}
