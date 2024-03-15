package com.example.t1_eirian_tta6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.t1_eirian_tta6.databinding.ActivityDetailsScreenBinding;
import com.example.t1_eirian_tta6.db.PokemonsManagerSingleton;
import com.example.t1_eirian_tta6.model.Pokemon;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class DetailsScreenActivity extends AppCompatActivity {
    private ActivityDetailsScreenBinding binding;
    private int pokedex = -1;
    private Pokemon currPokemon;
    // Declare shared preferences class property
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.sp = getSharedPreferences("MY-SP", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        if (intent != null) {
            Pokemon selectedPokemon = (Pokemon) intent.getSerializableExtra("Selected_Pokemon");
            pokedex = selectedPokemon.getPokedex();
            PokemonsManagerSingleton pokemonsManager = PokemonsManagerSingleton.getInstance();
            currPokemon = pokemonsManager.getPokemonById(pokedex);

            binding.tvName.setText("Name: " + currPokemon.getName());
            binding.tvPokedex.setText("Pokedex Number: " + currPokemon.getPokedex());

            int imageid = getResources().getIdentifier(currPokemon.getName().toLowerCase(Locale.ROOT), "drawable",
                    getPackageName());
            binding.ivAvatar.setImageResource(imageid);


            binding.btnFight.setOnClickListener(v-> {
                int firstNum = (int)Math.floor(Math.random()*5-1+1)+1;
                int secondNum= (int)Math.floor(Math.random()*5-1+1)+1;
                String winner;
                if (firstNum > secondNum) {
                    currPokemon.setWins(currPokemon.getWins()+1);
                    winner = currPokemon.getName();
                }
                else {
                    currPokemon.setLosses(currPokemon.getLosses()+1);
                    winner = "Computer";
                }
                Snackbar.make(binding.getRoot(),
                        "Player: " + firstNum + ", Computer: " + secondNum + ", Winner: " + winner,
                        Snackbar.LENGTH_LONG).show();
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_screen_options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miSetFav:
                setFavouritePokemon();
                return true;
            case R.id.miReset:
                resetPokemon();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setFavouritePokemon() {
        if (pokedex != -1) {
            SharedPreferences.Editor spEditor = sp.edit();
            spEditor.putString("KEY_"+pokedex,"");
            spEditor.apply();
            Snackbar.make(binding.getRoot(),"Favorited!",Snackbar.LENGTH_SHORT).show();
        }

    }

    private void resetPokemon() {
        if (pokedex != -1) {
            currPokemon.setWins(0);
            currPokemon.setLosses(0);
            SharedPreferences.Editor spEditor = sp.edit();
            spEditor.remove("KEY_"+pokedex);
            spEditor.commit();
            Snackbar.make(binding.getRoot(),"Pokemon Reset!",Snackbar.LENGTH_SHORT).show();
        }
    }
}