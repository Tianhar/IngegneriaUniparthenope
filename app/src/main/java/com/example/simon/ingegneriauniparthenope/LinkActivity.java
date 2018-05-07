package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.simon.ingegneriauniparthenope.Utility.newFacebookIntent;


public class LinkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button bottone_Gruppo = (Button) findViewById(R.id.bottoneGruppo);
        Button bottone_Learning = (Button) findViewById(R.id.bottoneLearning);
        Button bottone_esse3 = (Button) findViewById(R.id.bottoneEsse3);
        Button bottone_ateno = (Button) findViewById(R.id.bottoneSitoAteneo);

        bottone_Gruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbconn = newFacebookIntent(getPackageManager(), "https://it-it.facebook.com/ingegneria.uniparthenope/");
                startActivity(fbconn);
            }
        });

        bottone_Learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://edi.uniparthenope.it/"));
                startActivity(browserIntent);
            }
        });

        bottone_esse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://uniparthenope.esse3.cineca.it/Home.do"));
                startActivity(browserIntent);
            }
        });

        bottone_ateno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uniparthenope.it/"));
                startActivity(browserIntent);
            }
        });
    }
}





