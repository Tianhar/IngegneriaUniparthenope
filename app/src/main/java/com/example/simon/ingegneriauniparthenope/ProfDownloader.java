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

//Effettua il download del personale docenti

public class ProfDownloader extends AsyncTask<Void, Void, Void> {
    static public ArrayList<String> nome = new ArrayList<String>();
    static public ArrayList<String> telefono = new ArrayList<String>();
    static public ArrayList<String> studio = new ArrayList<String>();
    static public ArrayList<String> email = new ArrayList<String>();
    public static boolean erroreDownload = false;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String url = "http://www.ingegneria.uniparthenope.it/index.php?page=personale";
            InputStream input = new URL(url).openStream();
            Document doc = Jsoup.parse(input, "CP1252", url);
            Elements tables = doc.getElementsByClass("datatable");
            Elements rows = tables.get(0).getElementsByTag("tr");

            for (Element row : rows) {
                Elements colonne1 = row.getElementsByTag("th");
                Elements colonne2 = row.getElementsByTag("td");

                if (colonne1.size() == 0) {
                    nome.add(colonne2.get(0).text());
                    telefono.add(colonne2.get(1).text());
                    studio.add("Piano " + colonne2.get(2).text() + " - Lato " + colonne2.get(3).text() + " - Stanza " + colonne2.get(4).text());
                    email.add(colonne2.get(5).text());
                } else {
                    nome.add("Personale Docente");
                    telefono.add(" ");
                    studio.add(" ");
                    email.add(" ");
                }

            }
        } catch (IOException e) {
            erroreDownload = true;
        }
        return null;
    }

}

