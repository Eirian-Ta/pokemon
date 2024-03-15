package com.example.t1_eirian_tta6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t1_eirian_tta6.databinding.ActivityMainBinding;
import com.example.t1_eirian_tta6.db.PokemonsManagerSingleton;
import com.example.t1_eirian_tta6.model.Pokemon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Integer> favPokedexes = new ArrayList<>();
    private PokemonsManagerSingleton pokemonsManager;
    private SharedPreferences sp;
    private SharedPreferences.OnSharedPreferenceChangeListener listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pokemonsManager = PokemonsManagerSingleton.getInstance();
        ArrayList<Pokemon> pokemonsList = pokemonsManager.getPokemonArrayList();


        this.sp = getSharedPreferences("MY-SP", Context.MODE_PRIVATE);
        for (Integer num:pokemonsManager.getPokedexes()) {
            if (sp.contains("KEY_"+num) == true) {
                favPokedexes.add(num);
            }
        }

        PokemonObjAdapter pokemonsAdapter = new PokemonObjAdapter(this,pokemonsList, favPokedexes);
        binding.lvPokemons.setAdapter(pokemonsAdapter);

        listner = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                Log.d("*******************A2",key.substring(4));
                if (favPokedexes.contains(Integer.valueOf(key.substring(4)))) {
                    favPokedexes.remove(Integer.valueOf(key.substring(4)));
                    Log.d("1","removed");
                }
                else {
                    favPokedexes.add(Integer.valueOf(key.substring(4)));
                    Log.d("1","added");
                }
                pokemonsAdapter.notifyDataSetChanged();
            }
        };
        this.sp.registerOnSharedPreferenceChangeListener(listner);

        pokemonsAdapter.notifyDataSetChanged();

        binding.lvPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsScreenActivity.class);
                intent.putExtra("Selected_Pokemon",pokemonsList.get(position));
                startActivity(intent);
                //pokemonsAdapter.notifyDataSetChanged();
            }


        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        ((BaseAdapter) binding.lvPokemons.getAdapter()).notifyDataSetChanged();
    }
}