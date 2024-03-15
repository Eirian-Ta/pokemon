package com.example.t1_eirian_tta6;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.t1_eirian_tta6.databinding.CustomRowLayoutBinding;
import com.example.t1_eirian_tta6.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonObjAdapter extends ArrayAdapter {
    private List<Pokemon> pokemonsList;
    private ArrayList<Integer> favouritePokedexes;

    public PokemonObjAdapter(@NonNull Context context, List<Pokemon> pokemonsList, ArrayList<Integer> favouritePokedexes)
    {
        super(context, 0);
        this.pokemonsList = pokemonsList;
        this.favouritePokedexes = favouritePokedexes;
    }

    @Override
    public int getCount() {
        return pokemonsList.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_row_layout, parent, false);
        }

        Pokemon currPokemon = pokemonsList.get(position);

        CustomRowLayoutBinding binding = CustomRowLayoutBinding.bind(convertView);

        // @TODO: code to update the ui
        binding.tvPokemonName.setText(currPokemon.getName());
        binding.tvPokemonRecord.setText("Wins: " + currPokemon.getWins() + " - " + currPokemon.getLosses());
        if (favouritePokedexes.contains(currPokemon.getPokedex())) {
            binding.pokemonRow.setBackgroundColor(Color.YELLOW);
        }
        else {
            binding.pokemonRow.setBackgroundColor(Color.WHITE);
        }


        // Return the completed view to render on screen
        return convertView;
    }

}
