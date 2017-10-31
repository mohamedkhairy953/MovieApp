package com.example.moham.movieapp_volley.presenter.details;

import com.example.moham.movieapp_volley.model.Movie_ModelResults;

/**
 * Created by moham on 10/23/2017.
 */

public interface DeatailsPresenter {
    void doInitializeMovieIntent();

    void doFavBtnChange(int id);

    void doInsertMovieInFav(Movie_ModelResults movieIntent);

    void doLoadTrailers(int id);

    void doLoadReviews(int id);
}
