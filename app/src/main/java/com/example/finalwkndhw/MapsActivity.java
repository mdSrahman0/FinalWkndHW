package com.example.finalwkndhw;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String inputLatString;
    private String inputLngString;
    private double inputLat;
    private double inputLng;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFindLocation:
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                EditTextCustom editTextCustom = findViewById(R.id.etEnterCoords);
                String inputCoords = editTextCustom.getText().toString();
                Log.d("TAG", inputCoords);

                inputLatString = inputCoords.substring(0, inputCoords.indexOf(","));
                inputLngString = inputCoords.substring(inputCoords.indexOf(",")+1);
                inputLat = Double.parseDouble(inputLatString);
                inputLng = Double.parseDouble(inputLngString);

                location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(inputLat);
                location.setLongitude(inputLng);
                moveToLocation(location);
                break;

            case R.id.btnSeeForecast:
                if(location != null) {
                    Intent intent = new Intent(this, WeatherForecastRecyclerViewActivity.class);
                    intent.putExtra("passedLat", location);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please provide a latitude and longitude", Toast.LENGTH_LONG).show();
                }
                break;
        } // end switch
    }

    public void moveToLocation(Location location) {
        LatLng latLongOfLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLongOfLocation).title("Location of current call"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLongOfLocation, 17));
        // zoom in closer automatically
        //mMap.setMinZoomPreference(1);
    }
}
