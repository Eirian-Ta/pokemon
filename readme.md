# Introduction

The app has 2 screens:

1. Screen 1: displays a list of pokemon in a `ListView`.
   Clicking on a row will navigate the user to the **Details Screen**.

2. Screen 2: displays details about a specific pokemon.

- The screen looks up a pokemon using the pokemon’s pokedex number; and then using the
  retrieved information, displays the pokemon’s image, name, and pokedex number. The image of the pokemon is programmatically retrieved based on its corresponding file name in `drawables/` folder.
- There is a button labeled FIGHT COMPUTER: When the button is pressed, the screen initiates a fight between the selected Pokemon and the computer by generating two random numbers between 1 and 5.
- The screen also includes an Options Menu allowing users to set a pokemon as their favourite.

# Implementation

- The list of pokemon and all functions relating to retrieving a pokemon are performed
  using the Singleton pattern.
- **View bindings** is used through out the app, there's no **data bindings** or `findViewById()`
