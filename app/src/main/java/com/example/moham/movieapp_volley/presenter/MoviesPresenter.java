package com.example.moham.movieapp_volley.presenter;

import android.os.Bundle;

/**
 * Created by moham on 10/21/2017.
 */

public interface MoviesPresenter {
    void loadTopMovies(LoadInterfaceListener listener);

    void loadPopMovies(LoadInterfaceListener listener);

    void loadFavMovies(LoadInterfaceListener listener);

    void fetchMovies(Bundle savedInstanceState);

    void saveChanges(int topVotedListName);
}
