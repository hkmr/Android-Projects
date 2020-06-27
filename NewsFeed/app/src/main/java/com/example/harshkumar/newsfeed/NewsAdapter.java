package com.example.harshkumar.newsfeed;

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

public class NewsAdapter extends ArrayAdapter<News> {

    NewsAdapter(Context context, ArrayList<News> news){
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_card,parent,false);
        }

        News currentNews = getItem(position);

        TextView title = convertView.findViewById(R.id.news_title);
        title.setText(currentNews.getTitle());

        TextView author = convertView.findViewById(R.id.news_author);
        author.setText(currentNews.getAuthor());

        TextView date = convertView.findViewById(R.id.news_date);
        date.setText(currentNews.getDate());

        TextView tag = convertView.findViewById(R.id.news_tag);
        tag.setText(currentNews.getTag());

        ImageView img = convertView.findViewById(R.id.news_icon);
        img.setBackgroundColor(Color.parseColor("#1a76bc"));

        return convertView;
    }
}
