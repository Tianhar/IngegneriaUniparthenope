package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Classe ModulisticaActivity,
 * <p>Classe che si occupa di inizializzare activity modulistica.</p>
 * <p>Vengono creati i riferimenti a tutti i pulsanti.</p>
 * <p>Viene effettuato il parsing con tutte le pagine della modulsitica</p>
 */

public class ModulisticaActivity extends AppCompatActivity {
    /**
     * Metodo onCreate per activity_modulistica
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulistica);



        Button bottoneMod1 = (Button) findViewById(R.id.buttonModulo1);
        Button bottoneMod2 = (Button) findViewById(R.id.buttonModulo2);
        Button bottoneMod3 = (Button) findViewById(R.id.buttonModulo3);
        Button bottoneMod4 = (Button) findViewById(R.id.buttonModulo4);
        Button bottoneMod5 = (Button) findViewById(R.id.buttonModulo5);
        Button bottoneMod6 = (Button) findViewById(R.id.buttonModulo6);
        Button bottoneMod7 = (Button) findViewById(R.id.buttonModulo7);


        bottoneMod1.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/part.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneMod2.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/handicap.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneMod3.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/rinuncia.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneMod4.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/decadenza.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneMod5.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/modulo_richiesta_certificati.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneMod6.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/DOMANDA_PARTECIPAZIONE_SEDUTA_DI_LAUREA.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneMod7.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo per il parsing con la pagina della modulistica attraverso il relativo bottone
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/studenti/doc_studenti/Mod_rich_esami_a_scelta.pdf"));
                startActivity(browserIntent);
            }
        });


    }
}
