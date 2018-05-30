package com.example.simon.ingegneriauniparthenope;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Classe Mapsactivity,
 * <p>Classe per la gestione delle mappe</p>
 * <p>Dichiarazione di parametri di aggiornemtno della posizione (distanza minima e intervallo di tempo).</p>
 * <p>All'apertura delle mappe viene richiesto l'accesso ai servizi gps.</p>
 */


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;

    /**
     * Distanza minima per aggiornmento in metri (10)
     */
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    /**
     * Tempo minimo tra aggiornamento in millesecondi (1000)
     */
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1 * 1; // 1 second
    private LocationManager locationManager;
    /**
     * Variabili booleane per testare disponibilità gps e rete
     */
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    /**
     *
     */
    private ImageButton btnmyposition;

    private LatLng position = null;

    @Override
    /**
     * All'apertura della mappa, chiedo l'accesso al servizio gps per la localizzazione della posizione corrente
     */


    protected void onCreate(Bundle savedInstanceState) {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnmyposition = (ImageButton) findViewById(R.id.imageButton);
        btnmyposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent refresh = new Intent(MapsActivity.this, MapsActivity.class);
                refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(refresh);
                MapsActivity.this.finish();
            }


        });
    }

    /**
     * Metodo per la gestione del punto di partenza nei casi in cui la posizione è abilitata;non è abilitata o non viene individuata
     *
     * @return
     */

    public LatLng getLocation() {
        Location location;
        LatLng l = null;

        try {

            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
                Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                return l = new LatLng(40.852485, 14.272148); //se non abilitato posizione = Stazione P.zza Garibaldi

            } else {

                // se il GPS è abilitato prendo latitudine e longitudine attraverso il sensore GPS
                if (isGPSEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, getString(R.string.pos_not_allowed), Toast.LENGTH_LONG).show();
                        return l = new LatLng(40.852485, 14.272148);
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location == null) {
                            Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                            l = new LatLng(40.852485, 14.272148);
                            return l;

                        } else if (location.getLatitude() == 0.0 || location.getLongitude() == 0.0) {
                            Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                            return new LatLng(40.852485, 14.272148);
                        } else
                            Toast.makeText(this, getString(R.string.yesposition), Toast.LENGTH_LONG).show();
                        return new LatLng(location.getLatitude(), location.getLongitude());
                    }
                } else if (isNetworkEnabled) {

                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        Toast.makeText(this, getString(R.string.yesposition), Toast.LENGTH_LONG).show();
                        l = new LatLng(location.getLatitude(), location.getLongitude());
                        return l;
                    } else {
                        Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                        return l = new LatLng(40.852485, 14.272148);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
        return l = new LatLng(40.852485, 14.272148);
    }


///////////////////////////

    /**
     * Metodo per il posizionamento dei markers di partenza e di arrivo
     *
     * @param googleMap
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        position = getLocation();
        LatLng destination = new LatLng(40.857094, 14.284117);
        DrawRouteMaps.getInstance(this)
                .draw(position, destination, mMap);
        if (position.latitude != 40.852485 && position.longitude != 14.272148)
            DrawMarker.getInstance(this).draw(mMap, position, R.drawable.marker_a, getString(R.string.yourposition));
        else
            DrawMarker.getInstance(this).draw(mMap, position, R.drawable.marker_a, getString(R.string.garibaldi));
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.marker_b, getString(R.string.universityposition));

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(position)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));
    }

    /**
     * Metodo per verifica variazione location
     * @param location
     */

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            position = new LatLng(location.getLatitude(), location.getLongitude());
        }
    }

    /**
     * Metodo per verifica variazione stato
     * @param s
     * @param i
     * @param bundle
     */

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    /**
     *
     * @param s
     */

    @Override
    public void onProviderEnabled(String s) {

    }

    /**
     *
     * @param s
     */

    @Override
    public void onProviderDisabled(String s) {

    }


}