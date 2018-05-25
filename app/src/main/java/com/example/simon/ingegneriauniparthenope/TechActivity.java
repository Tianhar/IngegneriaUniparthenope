package com.example.simon.ingegneriauniparthenope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.simon.ingegneriauniparthenope.MainActivity.techd;

public class TechActivity extends AppCompatActivity {


    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_tech);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final TextView nomePersonale = (TextView) findViewById(R.id.nomePersonale);
        final TextView studioPersonale = (TextView) findViewById(R.id.studioPersonale);
        final TextView telefonoPersonale = (TextView) findViewById(R.id.telefonoPersonale);
        final TextView emailPersonale = (TextView) findViewById(R.id.emailPersonale);

        Spinner spinnerDoc = (Spinner) findViewById(R.id.spinnerDocenti);

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, techd.nome);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoc.setAdapter(spin_adapter);
        spinnerDoc.setSelection(0);
        spinnerDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                nomePersonale.setText(techd.nome.get(position).toString());
                studioPersonale.setText(techd.studio.get(position).toString());
                telefonoPersonale.setText(techd.telefono.get(position).toString());
                emailPersonale.setText(techd.email.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        /*
        if (techd.downloadStatus == 0) {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            if (indownloadt == true) {
                Toast.makeText(getApplicationContext(), R.string.downloadingStatus, Toast.LENGTH_SHORT).show();
                indownloadt = false;
            }
        }

        if (techd.erroreDownload == true) {
            Toast.makeText(getApplicationContext(), R.string.downloaderror, Toast.LENGTH_SHORT).show();
        }
        */

    }

}