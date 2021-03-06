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

/**
 * Classe NewsDownloader per il download delle news.
 * <p>Metodo doInBackground che effettua il parsing con la pagina delle nwws e scarica i vari elementi delle news.</p>
 *
 * <p>I vari elementi scaricati vengo caricati sui rispettivi ArrayList</p>
 *
 */

public class NewsDownloader extends AsyncTask<Void, Void, Void> {
    /**
     * Dichiarazione ArrayList per la data
     */
    public static ArrayList<String> newsData = new ArrayList<String>();
    /**
     * Dichiarazione ArrayList per il titolo
     */
    public static ArrayList<String> newsTitolo = new ArrayList<String>();
    /**
     * Dichirazione ArrayLiist per corpo
     */
    public static ArrayList<String> newsCorpo = new ArrayList<String>();
    /**
     * Variabile per eventuale errore download
     */
    public static boolean erroreDownload = false;
    /**
     * Variabile per lo stato del download
     */
    public static int downloadStatus = 0;

    /**
     * Metodo doInBackground che effettua il parsing con la pagina delle nwws e scarica i vari elementi delle news
     *
     * @param voids
     * @return
     */


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
                    } else if (ancor.attr("href").equals(ancor.text()) || (ancor.attr("href").equals("mailto:" + ancor.text()))) {
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
                erroreDownload = false;
            }
        } catch (IOException e) {
            erroreDownload = true;
        }
        downloadStatus = 1;
        return null;
    }

    /**
     * Metodo che verifica stato del download e presenza di eventuale errore settando downloadStatus
     */

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
