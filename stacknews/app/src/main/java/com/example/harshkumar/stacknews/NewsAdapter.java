package com.example.harshkumar.stacknews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshkumar.stacknews.data.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public static final String TAG = NewsAdapter.class.getSimpleName();

    private List<News> mNews;

    public NewsAdapter(Context context ,List<News> news){
        mNews = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card,viewGroup,false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {

        News currentNews = mNews.get(i);
        String newsTitle = currentNews.getmNewsTitle();
        String newsSource = currentNews.getmNewsSource();
        String newsImageUrl = currentNews.getmNewsImageUrl();
        String newsDate = currentNews.getmNewsDate();
        newsViewHolder.bind(newsTitle, newsSource, newsImageUrl, newsDate);

    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        public TextView newsHeaderTextView;
        public TextView newsDateTextView;
        public TextView newsProviderTextView;
        public ImageView newsImageView;

        public NewsViewHolder(View itemView){
            super(itemView);

            newsHeaderTextView = itemView.findViewById(R.id.news_heading);
            newsDateTextView = itemView.findViewById(R.id.news_date);
            newsProviderTextView = itemView.findViewById(R.id.news_provider);
            newsImageView = itemView.findViewById(R.id.news_image);

        }

        public void bind(String title,String newsSource,String imageUrl,String newsDate){

            newsHeaderTextView.setText(title);
            newsDateTextView.setText(newsDate);
            newsProviderTextView.setText(newsSource);

        }

    }

}
