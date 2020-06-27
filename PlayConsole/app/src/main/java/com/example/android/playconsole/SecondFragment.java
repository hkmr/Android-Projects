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
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container ,false);

        ArrayList<Game> games = new ArrayList<>();
        games.add(new Game("Title 1",1,3,"Play Station"));
        games.add(new Game("Title 2",1,3,"Play Station"));
        games.add(new Game("Title 3",1,3,"Play Station"));
        games.add(new Game("Title 4",1,3,"Play Station"));
        games.add(new Game("Title 5",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));
        games.add(new Game("Title 6",1,3,"Play Station"));

        GameAdapter gameAdapter = new GameAdapter(getActivity(),games);

        ListView listView = rootView.findViewById(R.id.ps_games);
        listView.setAdapter(gameAdapter);


        return rootView;
    }

}
