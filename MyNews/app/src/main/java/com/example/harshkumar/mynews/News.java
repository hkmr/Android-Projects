package com.example.harshkumar.mynews;

public class News {

    private String title;
    private String content;
    private String date;
    private String author;
    private String sectionName;
    private String url;

    public News(String title, String content, String date, String author, String sectionName,String url ){
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.sectionName = sectionName;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getUrl() {
        return url;
    }
}
