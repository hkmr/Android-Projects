package com.example.harshkumar.stacknews;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.harshkumar.stacknews.data.News;
import com.example.harshkumar.stacknews.utilities.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter mAdapter;
    private RecyclerView mNewsList;
    NewsLoader loader;
    private String urlString =
            "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=8d632abfccc14f5da6c09f95f2fafd42";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mNewsList = findViewById(R.id.news_cards);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNewsList.setLayoutManager(layoutManager);

        mAdapter = new NewsAdapter(this,new ArrayList<News>());
        mNewsList.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(1,null,this);

    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        loader = new NewsLoader(this,urlString);
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }
}
