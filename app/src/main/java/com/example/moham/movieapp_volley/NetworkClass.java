package com.example.moham.movieapp_volley;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.example.moham.movieapp_volley.model.Movie_Model;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.presenter.LoadInterfaceListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by moham on 10/22/2017.
 */

public class NetworkClass {
    private static final String API_KEY = "faa38d8564f66b5c1339d257bf6a6da9";

    private String WebAddress = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="
            + API_KEY;
    private String WebAddressVote = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="
            + API_KEY;


    public void getTopMovies(final LoadInterfaceListener listener) {
       Observable<Movie_Model> objectObservable = Rx2AndroidNetworking.get("http://api.themoviedb.org/3/discover/movie?sort_by=vote_average")
                .addQueryParameter("api_key", API_KEY)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(Movie_Model.class);
        objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie_Model>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movie_Model movie_models) {
                        Movie_ModelResults[] results = movie_models.getResults();
                        ArrayList<Movie_ModelResults> movie_modelResults = new ArrayList<>(Arrays.asList(results));
                        Log.d("yyyyyyyyy", movie_modelResults.get(0).getTitle());
                        listener.loadedSuccessfully(movie_modelResults);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.error(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPopMovies(final LoadInterfaceListener listener) {
        Observable<Movie_Model> objectObservable = Rx2AndroidNetworking.get("http://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", API_KEY)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(Movie_Model.class);
                objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie_Model>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movie_Model movie_models) {
                        Movie_ModelResults[] results = movie_models.getResults();
                        ArrayList<Movie_ModelResults> movie_modelResults = new ArrayList<>(Arrays.asList(results));
                        listener.loadedSuccessfully(movie_modelResults);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.error(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
