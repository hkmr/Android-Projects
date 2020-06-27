package com.example.harshkumar.sunshine;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.harshkumar.sunshine.utilities.NetworkUtils;

import java.net.URL;

public class Forecast extends AppCompatActivity {

    private TextView mWheatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);


        mWheatherTextView = findViewById(R.id.tv_weather_data);

    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String[]>{

        @Override
        protected String[] doInBackground(String... params) {

            if(params.length == 0){
                return null;
            }

            String location = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(location);

            try{
                String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

                String[] simpleJsonweatherData = 
            }
            catch (Exception e){

            }
            return new String[0];
        }
    }
}
