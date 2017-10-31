package com.example.moham.movieapp_volley.presenter.movies_presenter;

import android.os.Bundle;

import com.example.moham.movieapp_volley.model.Movie_ModelResults;

/**
 * Created by moham on 10/21/2017.
 */

public interface MoviesPresenter {
    void loadTopMovies(LoadInterfaceListener listener);

    void loadPopMovies(LoadInterfaceListener listener);

    void loadFavMovies(LoadInterfaceListener listener);

    void fetchMovies(Bundle savedInstanceState);

    void saveChanges(int topVotedListName);

    void doOnGridItemClicked(Movie_ModelResults movie);
}
