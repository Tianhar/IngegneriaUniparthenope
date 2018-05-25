package com.example.simon.ingegneriauniparthenope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.example.simon.ingegneriauniparthenope.Utility.checkConnectivity;
import static com.example.simon.ingegneriauniparthenope.Utility.newFacebookIntent;


/**
 * Main activity
 *
 * @author Gruppo
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Avvia il task che scarica le news
     */
    public static NewsDownloader newsd = (NewsDownloader) new NewsDownloader().execute();
    /**
     * Avvia il task che scarica i prof.
     */
    public static ProfDownloader profd = (ProfDownloader) new ProfDownloader().execute();
    /**
     * Avvia il task che scarica il personale
     */
    public static TechDownloader techd = (TechDownloader) new TechDownloader().execute();
    /**
     * Avvia il task che scarica parametri
     */
    public static Boolean indownloadn = true;
    /**
     * The constant indownloadt.
     */
    public static Boolean indownloadt = true;
    /**
     * The constant indownloadp.
     */
    public static Boolean indownloadp = true;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Metodo che crea e inizializza l'activity principale
     *
     * @param savedInstanceState
     */
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intent intentgpservice=new Intent(MainActivity.this,GPSTracker.class);
//startService(intentgpservice);
        setContentView(R.layout.activity_main);


        Button bottoneNews = (Button) findViewById(R.id.buttonOne);
        Button bottoneProf = (Button) findViewById(R.id.buttonTwo);
        Button bottoneLessons = (Button) findViewById(R.id.buttonThree);
        Button bottoneUtiliy = (Button) findViewById(R.id.buttonFour);
        Button bottoneTech = (Button) findViewById(R.id.buttonFive);
        Button bottoneMod = (Button) findViewById(R.id.buttonSix);


        if (checkConnectivity(this) == 0) {
            Toast.makeText(getApplicationContext(), R.string.nointernet, Toast.LENGTH_LONG).show();
        }


        ImageButton bottoneCS = (ImageButton) findViewById(R.id.buttonStudenti);


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


        bottoneNews.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire NewsActivity con relativo bottone.
             * @param v
             */

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });


        bottoneProf.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire ProfActivity con relativo bottone.
             *
             */

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfActivity.class));
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
                startActivity(new Intent(MainActivity.this, TechActivity.class));
            }
        });

        bottoneMod.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo che fa partire ModulisticaActivity con relativo bottone.
             *
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ModulisticaActivity.class));
            }
        });


    }

    /**
     * inizializza e gestisce menù a tendina
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.ITEM_1:

                startActivity(new Intent(MainActivity.this, MapsActivity.class));

                break;
            case R.id.ITEM_2:
                Intent fbconn = newFacebookIntent(getPackageManager(), "https://www.facebook.com/YouParthenope/");
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
                startActivity(refresh);
                this.finish();
                break;

        }
        return false;
    }

    /** Metodo verifica conessione e riesegue task download di prof news e tech*/
    @Override
    protected void onResume() {
        super.onResume();
        if ((checkConnectivity(this) == 1) && ((newsd.downloadStatus == 2) || (newsd.downloadStatus == 3))) {
            newsd.newsTitolo.clear();
            newsd.newsCorpo.clear();
            newsd.newsData.clear();
            newsd = (NewsDownloader) new NewsDownloader().execute();
        }
        if ((checkConnectivity(this) == 1) && ((profd.downloadStatus == 2) || (profd.downloadStatus == 3))) {
            profd.nome.clear();
            profd.telefono.clear();
            profd.email.clear();
            profd.studio.clear();
            profd = (ProfDownloader) new ProfDownloader().execute();
        }
        if ((checkConnectivity(this) == 1) && ((techd.downloadStatus == 2) || (techd.downloadStatus == 3))) {
            techd.nome.clear();
            techd.telefono.clear();
            techd.email.clear();
            techd.studio.clear();
            techd = (TechDownloader) new TechDownloader().execute();
        }

    }
}
