package com.example.kyrgyzpedigree;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_Maps extends AppCompatActivity implements GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnCameraIdleListener,
        OnMapReadyCallback {
    private TextView mTapTextView;
    private GoogleMap mMap;
    private Button okButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        mTapTextView = findViewById(R.id.positionText);
        okButton = findViewById(R.id.okButton);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng kyrgyzstan = new LatLng(42.882004, 74.582748);
        googleMap.addMarker(new MarkerOptions().position(kyrgyzstan)
                .title("Marker in KG"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(kyrgyzstan));
    }

    @Override
    public void onMapClick(LatLng point) {
        mTapTextView.setText("tapped, point=" + point);
    }

    @Override
    public void onMapLongClick(LatLng point) {
        mTapTextView.setText("long pressed, point=" + point);
    }

    @Override
    public void onCameraIdle() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
