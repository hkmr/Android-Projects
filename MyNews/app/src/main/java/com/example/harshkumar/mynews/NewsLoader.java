package com.example.harshkumar.mynews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    private String url;

    NewsLoader(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        if(url == null){
            return null;
        }

        List<News> news = QueryUtils.fetchData(url);
        return news;
    }
}
