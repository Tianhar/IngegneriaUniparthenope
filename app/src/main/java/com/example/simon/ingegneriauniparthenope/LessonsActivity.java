package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Button bottoneCorso1 = (Button) findViewById(R.id.buttonCorso1);
        Button bottoneCorso2 = (Button) findViewById(R.id.buttonCorso2);
        Button bottoneCorso3 = (Button) findViewById(R.id.buttonCorso3);
        Button bottoneCorso4 = (Button) findViewById(R.id.buttonCorso4);
        Button bottoneCorso5 = (Button) findViewById(R.id.buttonCorso5);
        Button bottoneCorso6 = (Button) findViewById(R.id.buttonCorso6);

        bottoneCorso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/orario/civile.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneCorso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/orario/civile_LM.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneCorso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/orario/gestionale.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneCorso4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/orario/gestionale_LM.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneCorso5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/orario/inf_bio_tlc.pdf"));
                startActivity(browserIntent);
            }
        });

        bottoneCorso6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ingegneria.uniparthenope.it/orario/ISDC.pdf"));
                startActivity(browserIntent);
            }
        });


    }
}