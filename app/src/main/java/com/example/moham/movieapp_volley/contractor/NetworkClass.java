package com.example.moham.movieapp_volley.contractor;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.example.moham.movieapp_volley.model.Movie_Model;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.model.Review_Model;
import com.example.moham.movieapp_volley.model.Review_ModelResults;
import com.example.moham.movieapp_volley.model.Trailer_Model;
import com.example.moham.movieapp_volley.model.Trailer_ModelResults;
import com.example.moham.movieapp_volley.presenter.details.ReviewInterfaceListener;
import com.example.moham.movieapp_volley.presenter.details.TrailerInterfaceListener;
import com.example.moham.movieapp_volley.presenter.movies_presenter.LoadInterfaceListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.Arrays;

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


    private String WebAddress =
            "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=faa38d8564f66b5c1339d257bf6a6da9";

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

    public void getTrailers(String id, final TrailerInterfaceListener listener) {
        Log.d("ssss", "sasasassas");
        Observable<Trailer_Model> objectObservable
                = Rx2AndroidNetworking.get("http://api.themoviedb.org/3/movie/{id}/videos?api_key=faa38d8564f66b5c1339d257bf6a6da9")
                .addPathParameter("id", id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(Trailer_Model.class);
        objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Trailer_Model>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Trailer_Model models) {
                        Trailer_ModelResults[] results = models.getResults();
                        Log.d("sssss", models.getId() + "");
                        ArrayList<Trailer_ModelResults> trailer_modelResults = new ArrayList<>(Arrays.asList(results));
                        listener.success(trailer_modelResults);

                    }

                    @Override
                    public void onError(Throwable e) {
                       e.printStackTrace();
                        listener.error(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getReviews(String id, final ReviewInterfaceListener listener) {
        Observable<Review_Model> objectObservable
                = Rx2AndroidNetworking.get("http://api.themoviedb.org/3/movie/{id}/reviews?api_key=faa38d8564f66b5c1339d257bf6a6da9")
                .addPathParameter("id", id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(Review_Model.class);
        objectObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Review_Model>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Review_Model movie_models) {
                        Review_ModelResults[] results = movie_models.getResults();
                        ArrayList<Review_ModelResults> review_modelResults = new ArrayList<>(Arrays.asList(results));
                        listener.success(review_modelResults);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
