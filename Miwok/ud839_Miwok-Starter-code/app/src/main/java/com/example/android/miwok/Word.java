package com.example.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageSourceId;
    private int mSoundSourceId;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imagesourceId, int soundSOurceId){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageSourceId = imagesourceId;
        this.mSoundSourceId = soundSOurceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int soundSourceId){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mSoundSourceId = soundSourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageSourceId(){
        return mImageSourceId;
    }

    public int getmSoundSourceId(){
        return mSoundSourceId;
    }

    public boolean hasImage(){
        if(this.mImageSourceId == 0){
            return false;
        }
        return true;
    }
}
