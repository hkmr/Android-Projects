package com.example.harshkumar.githubsearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.MalformedJsonException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshkumar.githubsearch.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultTextView;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSearchBoxEditText = findViewById(R.id.et_search_box);
        mUrlDisplayTextView = findViewById(R.id.tv_url_display);
        mSearchResultTextView = findViewById(R.id.tv_github_search_result_json);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

    }

    private void makeGithubSearchQuery(){
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        new GithubQueryTask().execute(githubSearchUrl);
    }

    public class GithubQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl =  urls[0];
            String githubSearchResults = null;

            try{
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            }catch(IOException e){
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(githubSearchResults != null && !githubSearchResults.equals("")){
                showJsonDataView();
//                mSearchResultTextView.setText(githubSearchResults);
                try{
                    showNames(new JSONObject(githubSearchResults));
                }catch (JSONException e){

                }

            }else{
                showErrorMessage();
            }

        }
    }

    private void showJsonDataView(){

        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mSearchResultTextView.setVisibility(View.VISIBLE);

    }

    private void showErrorMessage(){

        mSearchResultTextView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void showNames(JSONObject results){

        try{
            JSONArray items = results.getJSONArray("items");
            for(int i=0; i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                String name = item.getString("full_name");
                mSearchResultTextView.append(name+"\n\n");
            }

        }catch(JSONException e){

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemThatWasClickedId =  item.getItemId();
        if(itemThatWasClickedId == R.id.action_search){
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
