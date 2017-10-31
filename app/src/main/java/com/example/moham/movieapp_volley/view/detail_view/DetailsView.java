package com.example.moham.movieapp_volley.view.detail_view;

import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.model.Review_ModelResults;
import com.example.moham.movieapp_volley.model.Trailer_ModelResults;

import java.util.ArrayList;

/**
 * Created by moham on 10/22/2017.
 */

public interface DetailsView {
    void initializeMovieIntentFromFragment();
    void initializeMovieIntentFromActivity();

    void favBtnDisabling();

    void favBtnEnabling();

    void showConfirmToast(Movie_ModelResults movieIntent);

    void onTrailerLoaded(ArrayList<Trailer_ModelResults> trailers);

    void onLoadFailure(String error);

    void onReviewLoaded(ArrayList<Review_ModelResults> reviews);
}
