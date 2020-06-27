package com.example.harshkumar.mynews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final int LOADER_ID = 1;
    public static final String URL =
            "https://content.guardianapis.com/search?api-key=1e04b1ce-ced6-47e2-89cf-8d1c3c5df0d5&show-fields=all&show-tags=contributor&show-section=true&show-elements=all&show-references=author";
    private NewsAdapter mNewsAdapter;
    TextView emptyView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id =item.getItemId();
        if(id == R.id.setting_menu_item) {
            Intent settingIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = findViewById(R.id.news_list_view);
        mNewsAdapter = new NewsAdapter(this,new ArrayList<News>());
        newsListView.setAdapter(mNewsAdapter);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        emptyView = findViewById(R.id.empty_text_view);

        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(LOADER_ID,null,this);
        }else{
            View progressBar = findViewById(R.id.progess_bar);
            progressBar.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText(R.string.no_internet_connection);
        }

        newsListView.setEmptyView(emptyView);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                News currentNews = mNewsAdapter.getItem(i);
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(currentNews.getUrl()));
                startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String count = sharedPreferences.getString(
                getString(R.string.setting_news_count_key),
                getString(R.string.setting_news_count_default));

        Uri baseUri = Uri.parse(URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("page-size",count);

        return new NewsLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {

        View progressBar = findViewById(R.id.progess_bar);
        progressBar.setVisibility(View.GONE);

        emptyView.setText(R.string.no_data);

        if(news != null && !news.isEmpty()){
            mNewsAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }

}
