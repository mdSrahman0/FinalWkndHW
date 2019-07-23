package com.example.finalwkndhw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherForecastRecyclerViewActivity extends AppCompatActivity {

    private final String HOURLY_TEMP_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String TAG = "TAG";
    CompoundView compoundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast_recycler_view);
        compoundView = findViewById(R.id.cmpCompoundView);

        Log.d(TAG, "I'm HERE");
    }
}
