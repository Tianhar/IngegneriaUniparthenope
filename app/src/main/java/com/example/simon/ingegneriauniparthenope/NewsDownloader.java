package com.example.simon.ingegneriauniparthenope;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class NewsDownloader extends AsyncTask<Void, Void, Void> {
    public static ArrayList<String> newsData = new ArrayList<String>();
    public static ArrayList<String> newsTitolo = new ArrayList<String>();
    public static ArrayList<String> newsCorpo = new ArrayList<String>();
    public static boolean erroreDownload = false;
    public static int downloadStatus = 0;



    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String url = "http://www.ingegneria.uniparthenope.it/index.php?page=news";
            InputStream input = new URL(url).openStream();
            Document doc = Jsoup.parse(input, "CP1252", url);
            //Document doc = Jsoup.connect("http://www.ingegneria.uniparthenope.it/index.php?page=news").get();
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
                Elements list = link.getElementsByTag("li");

                for (Element ancor : link.select("a")) {
                    ancor.attr("href", ancor.attr("href").replace(" ", "%20"));
                    if (ancor.attr("href").substring(0, 1).equals(".")) {
                        ancor.text(ancor.text() + " ( " + "http://www.ingegneria.uniparthenope.it" + (ancor.attr("href").substring(1)) + " ) ");
                    } else if (ancor.attr("href").equals(ancor.text())) {
                        //Non fa niente
                    } else ancor.text(ancor.text() + " ( " + ancor.attr("href") + " ) ");
                }
                builder.append(contenuto.text()).append("\n");
                int ii = 1;

                for (Element liste : list) {
                    builder.append("\n").append("" + ii + "- " + liste.text()).append("\n");
                    ii++;
                }

                link.select("p").remove();
                link.select("h4").remove();
                link.select("ol").remove();
                link.select("ul").remove();
                Elements collegamenti = link.getElementsByTag("a");
                for (Element coll : collegamenti) {
                    builder.append("\n").append(coll.text()).append("\n");

                }
                newsCorpo.add(builder.toString());
            }
        } catch (IOException e) {
            erroreDownload = true;
        }
        downloadStatus = 1;
        return null;
    }

    @Override
    protected void onPostExecute(Void voids) {
        if ((downloadStatus == 1) && (erroreDownload == true)) {
            downloadStatus = 2;
        } else {
            if ((downloadStatus == 1) && (erroreDownload == false) && (newsData.size() == 0)) {
                downloadStatus = 3;
            }

        }
    }

}
