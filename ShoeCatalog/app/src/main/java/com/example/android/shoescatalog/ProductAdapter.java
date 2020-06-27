package com.example.android.shoescatalog;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    ProductAdapter(Context context, ArrayList<Product> obj){
        super(context,0,obj);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(
              R.layout.list_item_view,parent,false);
        }

        Product currentProduct = getItem(position);

//        setting each items to it's position

        TextView productName = (TextView) listView.findViewById(R.id.product_name);
        productName.setText(currentProduct.getmProductName());

        TextView productColor = (TextView) listView.findViewById(R.id.product_color);
        productColor.setText(currentProduct.getmProductColor());

        TextView productRating = (TextView) listView.findViewById(R.id.product_rating);
        productRating.setText(String.valueOf(currentProduct.getmProductRating()));

        TextView productRatingUsers = (TextView) listView.findViewById(R.id.product_rating_users);
        productRatingUsers.setText(String.valueOf(currentProduct.getmProductRatingUsers()));

        TextView productPrice = (TextView) listView.findViewById(R.id.product_price);
        productPrice.setText(String.valueOf(currentProduct.getmProductPrice()));

        TextView productOriginalPrice = (TextView) listView.findViewById(R.id.product_original_price);
        productOriginalPrice.setText(String.valueOf(currentProduct.getmOriginalProdcutPrice()));
        productOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        TextView discountPercent = (TextView) listView.findViewById(R.id.discount_percentage);
        discountPercent.setText(String.valueOf(currentProduct.getmProductDiscountPercentage())+"%");

        TextView productTags = (TextView) listView.findViewById(R.id.product_tags);
        productTags.setText(currentProduct.getmProductTags());

        ImageView productImage = (ImageView) listView.findViewById(R.id.product_image);
        productImage.setImageResource(R.drawable.ic_launcher_background);

        TextView productOffer = (TextView) listView.findViewById(R.id.offer_tag);
        if(currentProduct.ismIsOnOffer()){
            productOffer.setVisibility(View.VISIBLE);
        }else{
            productOffer.setVisibility(View.GONE);
        }

        return listView;
    }
}
