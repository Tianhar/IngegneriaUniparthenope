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
 * Classe ProfDownloader che effettua il download del personale docente.
 * <p>Metodo doInBackground che effettua il parsing con la pagina dei professori (Html) e scarica i vari elementi
 * della tabella table</p>
 *
 * <p>I vari elementi scaricati vengo caricati sui rispettivi ArrayList</p>
 */

public class ProfDownloader extends AsyncTask<Void, Void, Void> {
    /**
     * Dichiarazione ArrayList per nome dei professori
     */
    static public ArrayList<String> nome = new ArrayList<String>();
    /**
     * Dichiarazione ArrayList per telefono dei professori
     */
    static public ArrayList<String> telefono = new ArrayList<String>();
    /**
     * Dichiarazione ArrayList per studio dei professori
     */
    static public ArrayList<String> studio = new ArrayList<String>();
    /**
     * Dichiarazione ArrayList per emaildei professori
     */
    static public ArrayList<String> email = new ArrayList<String>();
    /**
     * Variabile per eventuale errore download
     */
    public static boolean erroreDownload = false;
    /**
     * Variabile per lo stato del download
     */
    public static int downloadStatus = 0;

    /**
     * Metodo doInBackground che effettua il parsing con la pagina dei professori e scarica i vari elementi della tabella table
     *
     * @param voids
     * @return
     */

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
     * @param voids
     */
    @Override
    protected void onPostExecute(Void voids) {
        if ((downloadStatus == 1) && (erroreDownload == true)) {
            downloadStatus = 2;
        } else {
            if ((downloadStatus == 1) && (erroreDownload == false) && (nome.size() == 0)) {
                downloadStatus = 3;
            }

        }
    }

}

