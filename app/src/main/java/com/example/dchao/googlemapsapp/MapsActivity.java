package com.example.dchao.googlemapsapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button openPopUp = (Button) findViewById(R.id.popUpButton);
        openPopUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLongInputFragment popup = new LatLongInputFragment();

                popup.show(popup.getFragmentManager(), "dialog");
                EditText lon = (EditText) findViewById(R.id.username);
                EditText lat = (EditText) findViewById(R.id.password);

                //hopefully get the longitude and latitude from the popup window
                int longitude = Integer.parseInt(lon.getText().toString());
                int latitude = Integer.parseInt(lat.getText().toString());

                if(longitude!= null && latitude != null) {
                    //store lat, lon to a parse object

                    mapFragment.getMap().addMarker(new MarkerOptions().position(new LatLng(10,40)).title("Marker"));
                }
            }
        });



    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}