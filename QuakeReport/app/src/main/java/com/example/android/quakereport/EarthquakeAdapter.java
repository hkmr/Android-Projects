package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    EarthquakeAdapter(Context context, ArrayList<Earthquake> obj){
        super(context,0,obj);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item_view,parent,false);
        }

        Earthquake currentData = getItem(position);

//        Magnitude
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        TextView magnitude = (TextView) listView.findViewById(R.id.magnitude);
        assert currentData != null;
        magnitude.setText(decimalFormat.format(currentData.getMagnitude()));


        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

//        Location spliting
        String location = currentData.getLocation();
        String[] splitLocation = new String[2];
        if(location.contains(" of")){
            splitLocation = location.split(" of",2);
        }else{
            splitLocation[0] = "near by";
            splitLocation[1] = location;
        }
        String offsetLocation = splitLocation[0];
        String primaryLocation = splitLocation[1];

        TextView location_offset = (TextView) listView.findViewById(R.id.location_offset);
        location_offset.setText(offsetLocation);
        TextView location_primary = (TextView) listView.findViewById(R.id.location_primary);
        location_primary.setText(primaryLocation);


//        Date and Time
        TextView time = (TextView) listView.findViewById(R.id.earthquake_time);
        TextView date = (TextView) listView.findViewById(R.id.earthquake_date);

        Date dateObj = new Date(Long.parseLong(currentData.getTime()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

        time.setText(timeFormat.format(dateObj));
        date.setText(dateFormat.format(dateObj));

        return listView;
    }

    private int getMagnitudeColor(Double colorValue){

        int magnitudeColorResourceId;
        int color = (int) Math.floor(colorValue);

        switch (color) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
