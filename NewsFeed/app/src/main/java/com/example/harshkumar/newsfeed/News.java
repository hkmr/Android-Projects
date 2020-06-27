package com.example.harshkumar.newsfeed;

public class News {

    private String title;
    private String author;
    private String tag;
    private String content;
    private String date;

    public News(String title, String author, String tag, String content, String date ){
        this.title = title;
        this.author = author;
        this.tag = tag;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
