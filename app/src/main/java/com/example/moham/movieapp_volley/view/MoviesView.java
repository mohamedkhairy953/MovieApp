package com.example.moham.movieapp_volley.view;

import com.example.moham.movieapp_volley.model.Movie_ModelResults;

import java.util.ArrayList;

/**
 * Created by moham on 10/21/2017.
 */

public interface MoviesView {
   void onLoadFailure(String errorMsg);
   void loadTopMovies(ArrayList<Movie_ModelResults> list);
   void loadPopMovies(ArrayList<Movie_ModelResults> list);
   void loadFavMovies(ArrayList<Movie_ModelResults> list);
}
