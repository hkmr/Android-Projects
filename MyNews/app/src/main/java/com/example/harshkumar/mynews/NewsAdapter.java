package com.example.harshkumar.mynews;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class NewsAdapter extends ArrayAdapter<News> {

    NewsAdapter(Context context, ArrayList<News> news){
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) throws NullPointerException {

        View RootView = convertView;
        if(RootView == null){
            RootView = LayoutInflater.from(getContext()).inflate(R.layout.news_card,
                    parent,false);
        }

        News currentNews = getItem(position);

        TextView title = RootView.findViewById(R.id.news_title);
        title.setText(currentNews.getTitle());

        TextView author = RootView.findViewById(R.id.news_author);
        author.setText(currentNews.getAuthor());

        TextView date = RootView.findViewById(R.id.news_date);
        date.setText(currentNews.getDate());

        TextView tag = RootView.findViewById(R.id.news_tag);
        tag.setText(currentNews.getSectionName());

        Random random = new Random();
        int color = Color.rgb(255, random.nextInt(256),random.nextInt(256));

        ImageView img = RootView.findViewById(R.id.news_color);
        img.setBackgroundColor(color);

        return RootView;
    }
}
