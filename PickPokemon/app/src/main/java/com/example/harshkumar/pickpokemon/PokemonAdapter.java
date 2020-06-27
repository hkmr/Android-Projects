package com.example.harshkumar.pickpokemon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    PokemonAdapter(Context context, ArrayList<Pokemon> pokemons){
        super(context,0,pokemons);
    }
}
