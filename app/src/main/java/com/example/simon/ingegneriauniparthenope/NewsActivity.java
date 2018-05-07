package com.example.simon.ingegneriauniparthenope;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


public class NewsActivity extends AppCompatActivity {
    int newsIndex = 0;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_news);

        final Spinner spinnerDate = (Spinner) findViewById(R.id.spinnerData);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView result = (TextView) findViewById(R.id.result);
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
                newsIndex++;
                spinnerDate.setSelection(newsIndex);

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
                newsIndex++;
                spinnerDate.setSelection(newsIndex);

            }


        });

        layout.setOnTouchListener(new OnSwipeTouchListener(NewsActivity.this) {
            public void onSwipeRight() {
                if (newsIndex == 0) {
                    Toast.makeText(getApplicationContext(), R.string.noPNews, Toast.LENGTH_SHORT).show();
                } else {
                    newsIndex--;
                    spinnerDate.setSelection(newsIndex);
                }

            }

            public void onSwipeLeft() {
                newsIndex++;
                spinnerDate.setSelection(newsIndex);

            }
        });

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, newsd.newsData);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(spin_adapter);
        spinnerDate.setSelection(newsIndex);
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                title.setText(newsd.newsTitolo.get(position).toString());
                result.setText(newsd.newsCorpo.get(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        Toast.makeText(getApplicationContext(), R.string.newsIndication, Toast.LENGTH_SHORT).show();

    }

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


