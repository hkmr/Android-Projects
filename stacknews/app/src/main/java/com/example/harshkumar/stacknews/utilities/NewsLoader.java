package com.example.harshkumar.stacknews.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.harshkumar.stacknews.data.News;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(@NonNull Context context,String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {

        if(mUrl == null){
            return null;
        }

        List<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }



}
