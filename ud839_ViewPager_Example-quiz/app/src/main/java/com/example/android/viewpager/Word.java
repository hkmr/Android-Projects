package com.example.android.viewpager;

public class Word {

    private String mVideoTitle;
    private int mViewsCount;
    private int mImageId;

    Word(String videoTitle, int viewCount, int imageId){
        this.mVideoTitle = videoTitle;
        this.mViewsCount = viewCount;
        this.mImageId = imageId;
    }

    public String getVideoTitle(){
        return this.mVideoTitle;
    }

    public int getViewsCount(){
        return this.mViewsCount;
    }

    public int getImageId(){
        return this.mImageId;
    }
}
