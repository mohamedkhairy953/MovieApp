package com.example.moham.movieapp_volley.presenter.details;

import android.util.Log;

import com.example.moham.movieapp_volley.contractor.DBController;
import com.example.moham.movieapp_volley.contractor.NetworkClass;
import com.example.moham.movieapp_volley.contractor.SharedPref;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.model.Review_ModelResults;
import com.example.moham.movieapp_volley.model.Trailer_ModelResults;
import com.example.moham.movieapp_volley.view.detail_view.DetailsView;

import java.util.ArrayList;

/**
 * Created by moham on 10/23/2017.
 */

public class DeatailsPresenterImpl implements DeatailsPresenter {
    private DetailsView view;
    private DBController dbController;
    private NetworkClass networkClass;

    public DeatailsPresenterImpl(DetailsView view, DBController dbController) {
        this.view = view;
        networkClass = new NetworkClass();
        this.dbController = dbController;
    }

    @Override
    public void doInitializeMovieIntent() {
        if (SharedPref.mTwoPane) {
            view.initializeMovieIntentFromFragment();
        } else {
            view.initializeMovieIntentFromActivity();
        }
    }

    @Override
    public void doFavBtnChange(int id) {
        if (dbController.is_In_MyFav(id)) {
            view.favBtnDisabling();
        } else {
            view.favBtnEnabling();

        }
    }

    @Override
    public void doInsertMovieInFav(Movie_ModelResults movieIntent) {
        long l = dbController.insert_movie(movieIntent);
        if (l > 0) {
            view.showConfirmToast(movieIntent);
            view.favBtnDisabling();
        }
    }

    @Override
    public void doLoadTrailers(int id) {
        Log.d("id mmmm",id+"");
         networkClass.getTrailers(String.valueOf(id), new TrailerInterfaceListener() {
            @Override
            public void success(ArrayList<Trailer_ModelResults> results) {
                view.onTrailerLoaded(results);

            }

            @Override
            public void error(String errorMsg) {
                view.onLoadFailure(errorMsg);
            }
        });

    }

    @Override
    public void doLoadReviews(int id) {
        Log.d("yoyo",id+"");

        networkClass.getReviews(String.valueOf(id), new ReviewInterfaceListener() {
            @Override
            public void success(ArrayList<Review_ModelResults> results) {
                view.onReviewLoaded(results);

            }

            @Override
            public void error(String errorMsg) {
                view.onLoadFailure(errorMsg);
            }
        });


    }

}
