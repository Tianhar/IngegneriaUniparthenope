package com.example.simon.ingegneriauniparthenope;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

import static com.example.simon.ingegneriauniparthenope.Utility.checkConnectivity;
import static com.example.simon.ingegneriauniparthenope.Utility.getSavedArrayList;
import static com.example.simon.ingegneriauniparthenope.Utility.newFacebookIntent;
import static com.example.simon.ingegneriauniparthenope.Utility.saveArrayList;


/**
 * Classe Main activity; classe principale che si occupa delle gestione di tutte
 * le activity.
 *
 * <p>Nel metodo onCreate viene inizializzata l'activity principale e vengono creati i riferimenti
 *  ai bottoni di ogni singolo tasto presenti in activty_main, inoltre si effettua un controllo
 *  sui permessi di rete e posizione.
 *
 *  <p>Successivamente vengono indicati i percorsi assoluti di tutti gli elementi relativi alle news, professori
 *  e personale amministrativo; il fine è quello di salvare i contenuti nei rispettivi ArrayList per essere utilizzati
 *  in mancanza di connessione.
 *
 * @version 1.0
 * @author Gruppo (Marco Martino, Vittorio Colonna, Simone Ardiero, Giuliano Riccio)
 * @since 30/04/2018
 */
public class MainActivity extends AppCompatActivity {

    /**</p>
     * Vengono avviati i vari task per scaricare le news,
     * i professori e il personale <p>
     */
    public static NewsDownloader newsd = (NewsDownloader) new NewsDownloader().execute();
    public static ProfDownloader profd = (ProfDownloader) new ProfDownloader().execute();
    public static TechDownloader techd = (TechDownloader) new TechDownloader().execute();
    public static Boolean insavingn = true;
    /**
     *
     */
    public static Boolean insavingt = true;
    /**
     *
     */
    public static Boolean insavingp = true;

    /**
     *
     * Metodo Che crea un menù a scelta nell'activity main.
     *
     *
     * */


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Metodo che crea e inizializza activity_main
     *
     * @param savedInstanceState
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        Button bottoneNews = findViewById(R.id.buttonOne);
        Button bottoneProf = findViewById(R.id.buttonTwo);
        Button bottoneLessons = findViewById(R.id.buttonThree);
        Button bottoneUtiliy = findViewById(R.id.buttonFour);
        Button bottoneTech = findViewById(R.id.buttonFive);
        Button bottoneMod = findViewById(R.id.buttonSix);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //&& ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET}, 1
            ); //Manifest.permission.ACCESS_COARSE_LOCATION,
        }


        if (checkConnectivity(this) == 0) {
            Toast.makeText(getApplicationContext(), R.string.nointernet, Toast.LENGTH_LONG).show();
            if ((NewsDownloader.newsData.size() == 0) && (ProfDownloader.nome.size() == 0) && (TechDownloader.nome.size() == 0) && (NewsDownloader.downloadStatus != 0) && (ProfDownloader.downloadStatus != 0) && (TechDownloader.downloadStatus != 0)) {
                String pathndata = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.newsfile_data);
                String pathntitolo = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.newsfile_titolo);
                String pathncontenuto = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.newsfile_contenuto);

                String pathpnome = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.proffile_nome);
                String pathpemail = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.proffile_email);
                String pathpstudio = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.proffile_studio);
                String pathptelefono = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.proffile_telefono);

                String pathtnome = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.techfile_nome);
                String pathtemail = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.techfile_email);
                String pathtstudio = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.techfile_studio);
                String pathttelefono = getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.techfile_telefono);

                //Per le news
                File filen1 = new File(pathndata);
                File filen2 = new File(pathntitolo);
                File filen3 = new File(pathncontenuto);

                //Per i prof
                File filep1 = new File(pathpnome);
                File filep2 = new File(pathpemail);
                File filep3 = new File(pathpstudio);
                File filep4 = new File(pathptelefono);

                //Per il personale tecnico-amministrativo
                File filet1 = new File(pathtnome);
                File filet2 = new File(pathtemail);
                File filet3 = new File(pathtstudio);
                File filet4 = new File(pathttelefono);

                if ((filen1.exists()) && (filen2.exists()) && (filen3.exists())) {
                    NewsDownloader.newsTitolo = getSavedArrayList(this, getResources().getString(R.string.newsfile_titolo));
                    NewsDownloader.newsCorpo = getSavedArrayList(this, getResources().getString(R.string.newsfile_contenuto));
                    NewsDownloader.newsData = getSavedArrayList(this, getResources().getString(R.string.newsfile_data));
                }

                if ((filep1.exists()) && (filep2.exists()) && (filep3.exists()) && (filep4.exists())) {
                    ProfDownloader.nome = getSavedArrayList(this, getResources().getString(R.string.proffile_nome));
                    ProfDownloader.telefono = getSavedArrayList(this, getResources().getString(R.string.proffile_telefono));
                    ProfDownloader.email = getSavedArrayList(this, getResources().getString(R.string.proffile_email));
                    ProfDownloader.studio = getSavedArrayList(this, getResources().getString(R.string.proffile_studio));
                }

                if ((filet1.exists()) && (filet2.exists()) && (filet3.exists()) && (filet4.exists())) {
                    TechDownloader.nome = getSavedArrayList(this, getResources().getString(R.string.techfile_nome));
                    TechDownloader.telefono = getSavedArrayList(this, getResources().getString(R.string.techfile_telefono));
                    TechDownloader.email = getSavedArrayList(this, getResources().getString(R.string.techfile_email));
                    TechDownloader.studio = getSavedArrayList(this, getResources().getString(R.string.techfile_studio));
                }
            }
        }

        if ((checkConnectivity(this) == 1) && ((NewsDownloader.downloadStatus == 2) || (NewsDownloader.downloadStatus == 3))) {
            NewsDownloader.newsTitolo.clear();
            NewsDownloader.newsCorpo.clear();
            NewsDownloader.newsData.clear();
            newsd = (NewsDownloader) new NewsDownloader().execute();
        }
        if ((checkConnectivity(this) == 1) && ((ProfDownloader.downloadStatus == 2) || (ProfDownloader.downloadStatus == 3))) {
            ProfDownloader.nome.clear();
            ProfDownloader.telefono.clear();
            ProfDownloader.email.clear();
            ProfDownloader.studio.clear();
            profd = (ProfDownloader) new ProfDownloader().execute();
        }
        if ((checkConnectivity(this) == 1) && ((TechDownloader.downloadStatus == 2) || (TechDownloader.downloadStatus == 3))) {
            TechDownloader.nome.clear();
            TechDownloader.telefono.clear();
            TechDownloader.email.clear();
            TechDownloader.studio.clear();
            techd = (TechDownloader) new TechDownloader().execute();
        }

        if ((checkConnectivity(this) == 1) && (TechDownloader.downloadStatus == 1) && (TechDownloader.erroreDownload == false) && (insavingt == true)) {
            //Salvo
            saveArrayList(this, TechDownloader.nome, getResources().getString(R.string.techfile_nome));
            saveArrayList(this, TechDownloader.telefono, getResources().getString(R.string.techfile_telefono));
            saveArrayList(this, TechDownloader.email, getResources().getString(R.string.techfile_email));
            saveArrayList(this, TechDownloader.studio, getResources().getString(R.string.techfile_studio));
            insavingt = false;
        }

        if ((checkConnectivity(this) == 1) && (ProfDownloader.downloadStatus == 1) && (ProfDownloader.erroreDownload == false) && (insavingp == true)) {
            //Salvo
            saveArrayList(this, ProfDownloader.nome, getResources().getString(R.string.proffile_nome));
            saveArrayList(this, ProfDownloader.telefono, getResources().getString(R.string.proffile_telefono));
            saveArrayList(this, ProfDownloader.email, getResources().getString(R.string.proffile_email));
            saveArrayList(this, ProfDownloader.studio, getResources().getString(R.string.proffile_studio));
            insavingp = false;
        }

        if ((checkConnectivity(this) == 1) && (NewsDownloader.downloadStatus == 1) && (NewsDownloader.erroreDownload == false) && (insavingn == true)) {
            //Salvo
            saveArrayList(this, NewsDownloader.newsData, getResources().getString(R.string.newsfile_data));
            saveArrayList(this, NewsDownloader.newsTitolo, getResources().getString(R.string.newsfile_titolo));
            saveArrayList(this, NewsDownloader.newsCorpo, getResources().getString(R.string.newsfile_contenuto));
            insavingn = false;
        }


        ImageButton bottoneCS = findViewById(R.id.buttonStudenti);


        bottoneCS.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per parsing sito cartastudenti al relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cartastudenti.org/?m=1"));
                startActivity(browserIntent);
            }
        });


        ImageButton bottoneDip = findViewById(R.id.dipartimento);


        bottoneDip.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per parsing sito cartastudenti al relativo bottone
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/"));
                startActivity(browserIntent);
            }
        });


        bottoneNews.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire NewsActivity al relativo bottone.
             * @param v
             */

            @Override
            public void onClick(View v) {
                if ((NewsDownloader.downloadStatus == 0)) {
                    Toast.makeText(getApplicationContext(), R.string.downloadingStatus, Toast.LENGTH_SHORT).show();
                } else if (NewsDownloader.newsData.isEmpty() == false) {
                    if ((checkConnectivity(MainActivity.this) == 1) && (NewsDownloader.erroreDownload == false) && (insavingn == true)) {
                        //Salvo
                        saveArrayList(MainActivity.this, NewsDownloader.newsData, getResources().getString(R.string.newsfile_data));
                        saveArrayList(MainActivity.this, NewsDownloader.newsTitolo, getResources().getString(R.string.newsfile_titolo));
                        saveArrayList(MainActivity.this, NewsDownloader.newsCorpo, getResources().getString(R.string.newsfile_contenuto));
                        insavingn = false;
                    }
                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                } else
                    Toast.makeText(getApplicationContext(), R.string.cant, Toast.LENGTH_SHORT).show();

            }
        });


        bottoneProf.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire ProfActivity con relativo bottone.
             *
             */

            @Override
            public void onClick(View v) {
                if ((ProfDownloader.downloadStatus == 0)) {
                    Toast.makeText(getApplicationContext(), R.string.downloadingStatus, Toast.LENGTH_SHORT).show();
                } else if (ProfDownloader.nome.isEmpty() == false) {
                    if ((checkConnectivity(MainActivity.this) == 1) && (ProfDownloader.downloadStatus == 1) && (ProfDownloader.erroreDownload == false) && (insavingp == true)) {
                        //Salvo
                        saveArrayList(MainActivity.this, ProfDownloader.nome, getResources().getString(R.string.proffile_nome));
                        saveArrayList(MainActivity.this, ProfDownloader.telefono, getResources().getString(R.string.proffile_telefono));
                        saveArrayList(MainActivity.this, ProfDownloader.email, getResources().getString(R.string.proffile_email));
                        saveArrayList(MainActivity.this, ProfDownloader.studio, getResources().getString(R.string.proffile_studio));
                        insavingp = false;
                    }
                    startActivity(new Intent(MainActivity.this, ProfActivity.class));
                } else
                    Toast.makeText(getApplicationContext(), R.string.cant, Toast.LENGTH_SHORT).show();

            }
        });

        bottoneUtiliy.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire LinkActivity con relativo bottone.
             *
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LinkActivity.class));
            }
        });


        bottoneLessons.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire LessonsActivity con relativo bottone.
             * @param v
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LessonsActivity.class));
            }
        });

        bottoneTech.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire TechActivity con relativo bottone.
             *
             */
            @Override
            public void onClick(View v) {
                if ((TechDownloader.downloadStatus == 0)) {
                    Toast.makeText(getApplicationContext(), R.string.downloadingStatus, Toast.LENGTH_SHORT).show();
                } else if (TechDownloader.nome.isEmpty() == false) {
                    if ((checkConnectivity(MainActivity.this) == 1) && (TechDownloader.downloadStatus == 1) && (TechDownloader.erroreDownload == false) && (insavingt == true)) {
                        //Salvo
                        saveArrayList(MainActivity.this, TechDownloader.nome, getResources().getString(R.string.techfile_nome));
                        saveArrayList(MainActivity.this, TechDownloader.telefono, getResources().getString(R.string.techfile_telefono));
                        saveArrayList(MainActivity.this, TechDownloader.email, getResources().getString(R.string.techfile_email));
                        saveArrayList(MainActivity.this, TechDownloader.studio, getResources().getString(R.string.techfile_studio));
                        insavingt = false;
                    }
                    startActivity(new Intent(MainActivity.this, TechActivity.class));
                } else
                    Toast.makeText(getApplicationContext(), R.string.cant, Toast.LENGTH_SHORT).show();

            }
        });

        bottoneMod.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire ModulisticaActivity al relativo bottone.
             *
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ModulisticaActivity.class));
            }
        });


    }

    /**
     * Metodo per la gestione di un menù.
     *
     *  <p> Menù a 4 bottoni:
     *  <p>1° fa partire MapsActivity evidenziando un segnalino sulla posizione dell'università.
     *  <p>2° tramite intent fa partire facebook con la pagina dell'università.
     *  <p>3° è un dialog con le info.
     *  <p>4° esce dall'applicazione.
     *
     *
     */


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toolbar toolbar = findViewById(R.id.onOptionsItemSelected);
        setSupportActionBar(toolbar);
        switch (id) {
            case R.id.ITEM_1:

                startActivity(new Intent(MainActivity.this, MapsActivity.class));

                break;
            case R.id.ITEM_2:
                Intent fbconn = newFacebookIntent(getPackageManager(), "https://www.facebook.com/StudentiPerUniparthenope/");
                startActivity(fbconn);
                break;
            case R.id.ITEM_3:

                final AlertDialog.Builder alertinfo = new AlertDialog.Builder(MainActivity.this);
                alertinfo.setTitle("Info").setMessage(getString(R.string.infoapp)).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }

                });
                AlertDialog dialoginfo = alertinfo.create();
                dialoginfo.show();
                break;

            case R.id.ITEM_4:
                this.finish();
                System.exit(0);
                break;

            case R.id.ITEM_6:
                Intent refresh = new Intent(this, MainActivity.class);
                refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(refresh);
                this.finish();
                break;

            case R.id.ITEM_7:

                final AlertDialog.Builder alertinfo2 = new AlertDialog.Builder(MainActivity.this);
                alertinfo2.setTitle("Info sugli sviluppatori").setMessage("Applicazione sviluppata da Giuliano Riccio, Simone Ardiero, Vittorio Colonna e Marco Martino").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                    }

                });
                AlertDialog dialoginfo2 = alertinfo2.create();
                dialoginfo2.show();
                break;


        }
        return false;
    }

    /**
     * Metodo che verifica lo stato della conessione e lo stato di downloadStatus
     *
     * <p>Se vi è connessione (1) e downloadStatus è nello stato 2 o 3 , pulisce titolo,
     * corpo e data e riesegue task per il download delle news
     *
     * <p>Analoga cosa accade per i task che scaricano i professori e
     * il personale
     *
     * */
    @Override
    protected void onResume() {
        super.onResume();
        if ((checkConnectivity(this) == 1) && ((NewsDownloader.downloadStatus == 2) || (NewsDownloader.downloadStatus == 3))) {
            NewsDownloader.newsTitolo.clear();
            NewsDownloader.newsCorpo.clear();
            NewsDownloader.newsData.clear();
            newsd = (NewsDownloader) new NewsDownloader().execute();
        }
        if ((checkConnectivity(this) == 1) && ((ProfDownloader.downloadStatus == 2) || (ProfDownloader.downloadStatus == 3))) {
            ProfDownloader.nome.clear();
            ProfDownloader.telefono.clear();
            ProfDownloader.email.clear();
            ProfDownloader.studio.clear();
            profd = (ProfDownloader) new ProfDownloader().execute();
        }
        if ((checkConnectivity(this) == 1) && ((TechDownloader.downloadStatus == 2) || (TechDownloader.downloadStatus == 3))) {
            TechDownloader.nome.clear();
            TechDownloader.telefono.clear();
            TechDownloader.email.clear();
            TechDownloader.studio.clear();
            techd = (TechDownloader) new TechDownloader().execute();
        }

    }
}
