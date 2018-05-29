package com.example.simon.ingegneriauniparthenope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.simon.ingegneriauniparthenope.MainActivity.profd;

/**
 * Classe ProfActivity per la sua gestione
 */
public class ProfActivity extends AppCompatActivity {
    /**
     *
     * Metodo onCreate che istanzia l'activity tech,
     * Riferimenti a TextView dei docenti (nome, studio, email, telefono),
     * Inserimento in uno Spinner
     *
     * @param SavedInstanceState
     *
     */


    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_prof);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final TextView nomeDocente = (TextView) findViewById(R.id.nomeDocente);
        final TextView studioDocente = (TextView) findViewById(R.id.studioDocente);
        final TextView telefonoDocente = (TextView) findViewById(R.id.telefonoDocente);
        final TextView emailDocente = (TextView) findViewById(R.id.emailDocente);

        Spinner spinnerDoc = (Spinner) findViewById(R.id.spinnerDocenti);

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, profd.nome);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoc.setAdapter(spin_adapter);
        spinnerDoc.setSelection(0);
        spinnerDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Metodo invocato quando viene selezionato un ogetto dallo spinner
             * @param adapterView
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                nomeDocente.setText(profd.nome.get(position).toString());
                studioDocente.setText(profd.studio.get(position).toString());
                telefonoDocente.setText(profd.telefono.get(position).toString());
                emailDocente.setText(profd.email.get(position).toString());
            }

            /**
             * Metodo quando non viene selezionato un ogetto dallo spinner
             * @param adapter
             */

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });
/*
        if (profd.downloadStatus == 0) {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            if (indownloadp == true) {
                Toast.makeText(getApplicationContext(), R.string.downloadingStatus, Toast.LENGTH_SHORT).show();
                indownloadp = false;
            }
        }

        if (profd.erroreDownload == true) {
            Toast.makeText(getApplicationContext(), R.string.downloaderror, Toast.LENGTH_SHORT).show();
        }
        */

    }


}




