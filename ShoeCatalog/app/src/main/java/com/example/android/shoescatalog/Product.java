package com.example.android.shoescatalog;

public class Product {

    private String mProductName;
    private String mProductColor;
    private float mProductRating;
    private int mProductRatingUsers;
    private int mProductPrice;
    private int mOriginalProductPrice;
    private float mProductDiscountPercentage;
    private String mProductTags;
    private boolean mIsOnOffer;
    private int mProductImageId;

    public Product(String productName, String productColor, float productRating, int productRatingUsers,
                   int productPrice, int productOriginalPrice, float productDiscountPercentage,
                   String productTags, int productImageId, boolean isOnOffer){
        this.mProductName = productName;
        this.mProductPrice = productPrice;
        this.mProductColor = productColor;
        this.mProductRating = productRating;
        this.mProductRatingUsers = productRatingUsers;
        this.mProductPrice = productPrice;
        this.mOriginalProductPrice = productOriginalPrice;
        this.mProductDiscountPercentage = productDiscountPercentage;
        this.mProductTags = productTags;
        this.mProductImageId = productImageId;
        this.mIsOnOffer = isOnOffer;
    }

    public String getmProductName(){
        return this.mProductName;
    }
    public String getmProductColor(){
        return this.mProductColor;
    }
    public float getmProductRating(){
        return this.mProductRating;
    }
    public int getmProductRatingUsers(){
        return this.mProductRatingUsers;
    }
    public int getmProductPrice(){
        return this.mProductPrice;
    }
    public int getmOriginalProdcutPrice(){
        return this.mOriginalProductPrice;
    }
    public float getmProductDiscountPercentage(){
        return this.mProductDiscountPercentage;
    }
    public String getmProductTags() {
        return mProductTags;
    }

    public int getmProductImageId() {
        return mProductImageId;
    }

    public boolean ismIsOnOffer() {
        return mIsOnOffer;
    }

}
