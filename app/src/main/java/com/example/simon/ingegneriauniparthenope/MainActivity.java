package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //Connessione a Facebook
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        Button bottoneNews = (Button) findViewById(R.id.buttonOne);
        Button bottoneProf = (Button) findViewById(R.id.buttonTwo);
        Button bottoneLessons = (Button) findViewById(R.id.buttonThree);

        ImageButton bottoneCS = (ImageButton) findViewById(R.id.buttonStudenti);

        bottoneCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cartastudenti.org/?m=1"));
                startActivity(browserIntent);
            }
        });


        bottoneNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });


        bottoneProf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfActivity.class));
            }
        });


        bottoneLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LessonsActivity.class));
            }
        });


    }

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

                MainActivity.this.finish();

        }
        return false;
    }

}
