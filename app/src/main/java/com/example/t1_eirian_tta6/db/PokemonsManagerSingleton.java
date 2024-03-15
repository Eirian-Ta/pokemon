package com.example.t1_eirian_tta6.db;

import com.example.t1_eirian_tta6.model.Pokemon;

import java.util.ArrayList;

public class PokemonsManagerSingleton {

    // boilerplate
    private PokemonsManagerSingleton() {}

    // instance
    private static PokemonsManagerSingleton instance = null;
    public static PokemonsManagerSingleton getInstance() {
        if (instance == null) {
            instance = new PokemonsManagerSingleton();
            instance.loadPokemonsDataSet();
        }
        return instance;
    }

    // properties
    private ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();


    // methods
    private void loadPokemonsDataSet() {
        this.pokemonArrayList.add(new Pokemon(12,"Caterpie"));
        this.pokemonArrayList.add(new Pokemon(19,"Rattata"));
        this.pokemonArrayList.add(new Pokemon(25,"Pikachu"));
        this.pokemonArrayList.add(new Pokemon(147,"Dratini"));

    }

    // getter
    public ArrayList<Pokemon> getPokemonArrayList() {
        return pokemonArrayList;
    }

    // helpers
    public ArrayList<Integer> getPokedexes() {
        ArrayList<Integer> pokedexes = new ArrayList<>();
        for (int i=0;i<this.pokemonArrayList.size();i++) {
            pokedexes.add(this.pokemonArrayList.get(i).getPokedex());
        }
        return pokedexes;
    }

    public Pokemon getPokemonById(int pokedexNumber) {
        for (int i=0;i<this.pokemonArrayList.size();i++) {
            Pokemon currPokemon = this.pokemonArrayList.get(i);
            if (currPokemon.getPokedex() == pokedexNumber) {
                return currPokemon;
            }
        }
        return null;
    }
}
