package com.example.android.playconsole;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView  = inflater.inflate(R.layout.fragment_first, container,false);

        ArrayList<Game> games = new ArrayList<>();
        games.add(new Game("First game",0,2,"pc"));
        games.add(new Game("Second game",0,3,"pc"));
        games.add(new Game("Third game",0,4,"pc"));
        games.add(new Game("Fourth game",0,5,"pc"));

        GameAdapter gameAdapter = new GameAdapter(getActivity(),games);

        ListView listView = rootView.findViewById(R.id.pc_games);

        listView.setAdapter(gameAdapter);

        return rootView;
    }

}
