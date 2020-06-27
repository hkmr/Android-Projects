package com.example.harshkumar.stacknews.data;

public class News {

    private String mNewsTitle;
    private String mNewsSource;
    private String mNewsAuthor;
    private String mNewsDescription;
    private String mNewsUrl;
    private String mNewsImageUrl;
    private String mNewsDate;
    private String mNewsContent;


    public News(String title,String newsSource, String author, String description, String newsUrl, String imageUrl,
                String newsDate,String newsContent){
        this.mNewsTitle = title;
        this.mNewsSource = newsSource;
        this.mNewsAuthor = author;
        this.mNewsDescription = description;
        this.mNewsUrl = newsUrl;
        this.mNewsImageUrl = imageUrl;
        this.mNewsDate = newsDate;
        this.mNewsContent = newsContent;

    }

    public String getmNewsTitle() {
        return mNewsTitle;
    }

    public String getmNewsSource() {
        return mNewsSource;
    }

    public String getmNewsAuthor() {
        return mNewsAuthor;
    }

    public String getmNewsDescription() {
        return mNewsDescription;
    }

    public String getmNewsUrl() {
        return mNewsUrl;
    }

    public String getmNewsImageUrl() {
        return mNewsImageUrl;
    }

    public String getmNewsDate() {
        return mNewsDate;
    }

    public String getmNewsContent() {
        return mNewsContent;
    }
}
