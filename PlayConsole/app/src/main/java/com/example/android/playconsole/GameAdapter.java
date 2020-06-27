package com.example.android.playconsole;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<Game> {

    GameAdapter(Context context, ArrayList<Game> games){
        super(context,0, games);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_list,parent,false);
        }

        Game game = getItem(position);


        TextView title = convertView.findViewById(R.id.game_title);
        TextView platform = convertView.findViewById(R.id.game_platform);

        title.setText(game.getTitle());
        platform.setText(game.getPlatform());


        return convertView;
    }
}
