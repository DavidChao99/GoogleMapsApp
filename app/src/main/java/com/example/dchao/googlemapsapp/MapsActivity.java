package com.example.dchao.googlemapsapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LatLongInputFragment.OnDialogDismissListener {
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        final Button openPopUp = (Button) findViewById(R.id.popUpButton);
        openPopUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLongInputFragment popup = new LatLongInputFragment();

                popup.show(getFragmentManager(), "dialog");
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


    public void onDialogDismissListener(int position) {
        LayoutInflater factory = getLayoutInflater();
        final View popupText = factory.inflate(R.layout.popup, null);

        // Do something here to display that article
        EditText lon = (EditText) popupText.findViewById(R.id.username);
        EditText lat = (EditText) popupText.findViewById(R.id.password);

        String a = lon.getText().toString().trim();
        String b = lat.getText().toString().trim();

        if(!a.equals("") && !b.equals("")) {
            //hopefully get the longitude and latitude from the popup window
            int longitude = Integer.parseInt(lon.getText().toString().trim());
            int latitude = Integer.parseInt(lat.getText().toString().trim());
            System.out.println(longitude + " " + latitude);

            //store lat, lon to a parse object

            mapFragment.getMap().addMarker(new MarkerOptions().position(new LatLng(10, 40)).title("Marker"));
        }
        else {
            AlertDialog.Builder a1 = new AlertDialog.Builder(this);


            // Setting Dialog Title
            a1.setTitle("Alert Dialog");

            // Setting Dialog Message
            a1.setMessage("PLEASE ENTER SOMETHING");

            a1.setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int button1) {
                            // if this button is clicked, close
                            // current activity
                            dialog.cancel();
                        }

                    });

            // Showing Alert Message
            AlertDialog alertDialog = a1.create();
            a1.show();
        }
    }

}