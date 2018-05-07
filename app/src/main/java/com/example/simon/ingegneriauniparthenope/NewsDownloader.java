package com.example.simon.ingegneriauniparthenope;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class NewsDownloader extends AsyncTask<Void, Void, Void> {
    public static ArrayList<String> newsData = new ArrayList<String>();
    public static ArrayList<String> newsTitolo = new ArrayList<String>();
    public static ArrayList<String> newsCorpo = new ArrayList<String>();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Document doc = Jsoup.connect("http://www.ingegneria.uniparthenope.it/index.php?page=news").get();
            String title = doc.title();
            Element content = doc.getElementById("content");
            Elements newsc = content.getElementsByClass("news");

            for (Element link : newsc) {
                StringBuilder builder = new StringBuilder();
                Elements dates = link.getElementsByClass("data");
                int darimuovere = dates.text().length();
                newsData.add(dates.text().substring(0, darimuovere - 1));
                Elements titles = link.getElementsByTag("h4");
                newsTitolo.add(titles.text().substring(darimuovere + 1));
                Elements contenuto = link.getElementsByTag("p");
                String cont = contenuto.text().replaceAll("’", "'");
                Elements list = link.getElementsByTag("li");
                builder.append(cont).append("\n");
                int ii = 1;
                for (Element liste : list) {
                    builder.append("\n").append("" + ii + "- " + liste.text()).append("\n");
                    ii++;
                }
                newsCorpo.add(builder.toString());
            }
        } catch (IOException e) {

        }
        return null;
    }

}
