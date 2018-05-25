package com.example.simon.ingegneriauniparthenope;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

//import com.ahmadrosid.lib.drawroutemap.DrawMarker;
//import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;

    // la minima distanza di aggiornamenti in metri
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // tempo minimo tra aggiornamenti in millisecondi
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1 * 1; // 1 second
    private ProgressDialog progressDialog;
    private String mode, avoid;
    private LocationManager locationManager;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean canGetLocation;
    private ImageButton btnDirections;

    //private String destination = "Via Marina Chiaiolella, 33, 80079 Procida";
    private LatLng position = null;

    @Override
    //Quando ricevo la mappa controllo lo stato della rete e del gps e localizzo la mia posizione

    protected void onCreate(Bundle savedInstanceState) {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    public LatLng getLocation() {
        Location location;
        LatLng l = null;

        try {

            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
                Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                return l = new LatLng(40.853170, 14.272306); //se non abilitato posizione = Stazione Napoli Centrale

            } else {

                // se il GPS è abilitato prendo latitudine e longitudine attraverso il sensore GPS
                if (isGPSEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return l = new LatLng(40.853170, 14.272306);
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location == null) {
                            Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                            l = new LatLng(40.853170, 14.272306);
                            return l;

                        } else if (location.getLatitude() == 0.0 || location.getLongitude() == 0.0) {
                            Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
                            return new LatLng(40.853170, 14.272306);
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
                        return l = new LatLng(40.853170, 14.272306);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, getString(R.string.noposition), Toast.LENGTH_LONG).show();
        return l = new LatLng(40.853170, 14.272306);
    }


///////////////////////////


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        position = getLocation();
        LatLng destination = new LatLng(40.857094, 14.284117);
        DrawRouteMaps.getInstance(this)
                .draw(position, destination, mMap);
        DrawMarker.getInstance(this).draw(mMap, position, R.drawable.marker_a, "La tua posizione");
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.marker_b, "Dipartimento di Ingegneria - Università Parthenope");

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(position)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            position = new LatLng(location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}