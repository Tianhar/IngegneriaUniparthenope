package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.simon.ingegneriauniparthenope.MainActivity.newsd;

/**
 * Classe News Activity per la sua gestione.
 * <p>Metodo onCreate per activity_news<p>
 * <p>Inserimento delle date in uno spinner</p>
 * <p>Ascoltatori di swipe (destra sinistra)</p>
 * <p>Caso di scorrimento oltre la news più recente o meno recente</p>
 *
 *
 */
public class NewsActivity extends AppCompatActivity {
    int newsIndex = 0;

    /**
     * Metodo che crea un menù nella stessa activity
     *
     * @param menu
     * @return
     */


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    /**
     * Metodo onCreate per activity_news,
     * Riferimenti TextView,
     * Ascoltatori di swipe,
     *
     * @param SavedInstanceState
     */

    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_news);

        final Spinner spinnerDate = (Spinner) findViewById(R.id.spinnerData);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView result = (TextView) findViewById(R.id.result);
        result.setMovementMethod(LinkMovementMethod.getInstance());
        final TextView title = (TextView) findViewById(R.id.title);
        final View scroll = (View) findViewById(R.id.srollView);
        final View layout = (View) findViewById(R.id.verticalLayout);


        //Ascoltatori di Swipe
        scroll.setOnTouchListener(new OnSwipeTouchListener(NewsActivity.this) {
            public void onSwipeRight() {
                if (newsIndex == 0) {
                    Toast.makeText(getApplicationContext(), R.string.noPNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex--;
                    spinnerDate.setSelection(newsIndex);
                }

            }

            public void onSwipeLeft() {
                if (newsIndex == (newsd.newsData.size() - 1)) {
                    Toast.makeText(getApplicationContext(), R.string.noNNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex++;
                    spinnerDate.setSelection(newsIndex);
                }

            }


        });


        result.setOnTouchListener(new OnSwipeTouchListener(NewsActivity.this) {
            public void onSwipeRight() {
                if (newsIndex == 0) {
                    Toast.makeText(getApplicationContext(), R.string.noPNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex--;
                    spinnerDate.setSelection(newsIndex);
                }

            }

            public void onSwipeLeft() {
                if (newsIndex == (newsd.newsData.size() - 1)) {
                    Toast.makeText(getApplicationContext(), R.string.noNNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex++;
                    spinnerDate.setSelection(newsIndex);
                }
            }


        });

        layout.setOnTouchListener(new OnSwipeTouchListener(NewsActivity.this) {
            /**
             * Metodo per scorrere oltre la news più recente
             */
            public void onSwipeRight() {
                if (newsIndex == 0) {
                    Toast.makeText(getApplicationContext(), R.string.noPNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex--;
                    spinnerDate.setSelection(newsIndex);
                }

            }

            /**
             * Metodo per scorrere oltre la news meno recente
             */

            public void onSwipeLeft() {
                if (newsIndex == (newsd.newsData.size() - 1)) {
                    Toast.makeText(getApplicationContext(), R.string.noNNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex++;
                    spinnerDate.setSelection(newsIndex);
                }
            }
        });


        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, newsd.newsData);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(spin_adapter);
        spinnerDate.setSelection(newsIndex);
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Metodo per l'estrazione di corpo e titolo news dai rispettivi ArrayList,
             * L'indice di posizione è lo stesso della data selezionata da spinner,
             * Inserimento di titolo e corpo nella textview
             *
             * @param adapterView
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                title.setText(newsd.newsTitolo.get(position).toString());
                result.setText(newsd.newsCorpo.get(position).toString());
                newsIndex = position;

            }

            /**
             * Non viene selezionato nulla
             * @param adapter
             */

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });


        if (newsd.newsData.isEmpty() == false) {

            Toast.makeText(getApplicationContext(), R.string.newsIndication, Toast.LENGTH_SHORT).show();

        }


    }

    /**
     *
     * <p>Riporta alla pagine ufficiale delle news quando questa è selezionata dal menù.<p>
     * @param item
     * @return
     */

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.ITEM_5:
                String url = "http://www.ingegneria.uniparthenope.it/index.php?page=news";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

        }
        return false;
    }
}


