package com.example.harshkumar.newsfeed;

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

public class QueryUtils {

    public static final String LOG_TAG = MainActivity.class.getName();

    private QueryUtils(){}

    public static List<News> fetchNewsData(String requestUrl){

        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem making the HTTP request",e);
        }

        List<News> news = extractFeatureFromJson(jsonResponse);

        return news;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;

        try{
            url = new URL(stringUrl);
        }catch(MalformedURLException e){

        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = null;

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
        }
        catch (IOException e){
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
            InputStreamReader inputStreamReader =  new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader reader =  new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String newsJson){

        if(TextUtils.isEmpty(newsJson)){
            return null;
        }

        List<News> news = new ArrayList<>();

        try{
            JSONObject reader = new JSONObject(newsJson);
            JSONObject response = reader.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            int i=0;
            while(i < results.length()){
                JSONObject obj = results.getJSONObject(i);

                String title = obj.getString("webTitle");

                JSONArray tags = obj.getJSONArray("tags");
                int j=0;
                StringBuilder auth = null;
                while(j < tags.length()){
                    JSONObject tagsObject = tags.getJSONObject(j);
                    if(tagsObject.getString("type").equalsIgnoreCase("contributor")){
                        auth.append(tagsObject.getString("webTitle"));
                        auth.append("and");
                    }
                }

                String author = auth.toString();

                JSONObject fields = obj.getJSONObject("fields");
                String content = fields.getString("bodyText");

                String date = fields.getString("firstPublicationDate");

                news.add(new News(title,author,null,content,date));
                Log.i(LOG_TAG,title);
                Log.i(LOG_TAG,author);
                Log.i(LOG_TAG,content);
                Log.i(LOG_TAG,date);

                i++;
            }
        }
        catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        return news;
    }
}
