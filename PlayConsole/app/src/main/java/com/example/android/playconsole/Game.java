package com.example.android.playconsole;

public class Game {

    private String title;
    private int region;
    private int rarity;
    private String platform;


    public Game(String title, int region, int rarity, String platform){
        this.title = title;
        this.region = region;
        this.rarity = rarity;
        this.platform = platform;
    }

    public String getTitle(){
        return this.title;
    }

    public int getRegion() {
        return region;
    }

    public int getRarity() {
        return rarity;
    }

    public String getPlatform() {
        return platform;
    }
}
