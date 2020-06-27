package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

public class QueryUtils {

    private QueryUtils() {
    }

    public static List<Earthquake> fetchEarthquakeData(String requestUrl){

        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            Thread.sleep(1000);
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem making the HTTP request",e);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        List<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);

        return earthquakes;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch(MalformedURLException e){
            Log.e(LOG_TAG,"Problem with the URL",e);
        }

        return url;
    }

    private static String makeHttpRequest(URL url ) throws IOException{
        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try{

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG,"Error response code"+httpURLConnection.getResponseCode());
            }

        }catch (IOException e){
                Log.e(LOG_TAG, "problem retriving the earthquake JSON result",e);
        }
        finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();

        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName(
                    "UTF-8"));

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return  output.toString();
    }

    public static List<Earthquake> extractFeatureFromJson(String earthquakeJson) {

        if(TextUtils.isEmpty(earthquakeJson)){
            return null;
        }

        List<Earthquake> earthquakes = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(earthquakeJson);

            JSONArray jsonArray = reader.getJSONArray("features");

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject properties = obj.getJSONObject("properties");

                earthquakes.add(new Earthquake(properties.getDouble("mag"),
                        properties.getString("place"),
                        properties.getString("time"),
                        properties.getString("url")));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}
