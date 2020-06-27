package com.example.android.quakereport;

import java.sql.Timestamp;

public class Earthquake {

    private Double mMagnitude;
    private String mlocation;
    private String mtime;
    private String url;

    public Earthquake(Double mMagnitude,String mlocation, String mtime, String url){
        this.mMagnitude = mMagnitude;
        this.mlocation = mlocation;
        this.mtime = mtime;
        this.url = url;
    }

    public Double getMagnitude(){
        return this.mMagnitude;
    }
    public String getLocation(){
        return this.mlocation;
    }
    public String getTime(){
        return this.mtime;
    }
    public String getUrl(){
        return this.url;
    }

}
