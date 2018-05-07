package com.example.simon.ingegneriauniparthenope;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng centrodirezionale = new LatLng(40.857094, 14.284117);

        MarkerOptions markerOpt = new MarkerOptions()
                .position(centrodirezionale);
        mMap.addMarker(markerOpt.position(centrodirezionale).title("Dipartimento di Ingegneria - Univesrità Parthenope"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centrodirezionale, 12));


    }
}
