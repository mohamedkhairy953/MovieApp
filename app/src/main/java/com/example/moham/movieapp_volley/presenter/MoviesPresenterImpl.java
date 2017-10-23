package com.example.moham.movieapp_volley.presenter;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.moham.movieapp_volley.DBController;
import com.example.moham.movieapp_volley.NetworkClass;
import com.example.moham.movieapp_volley.Utitlity;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.view.MoviesView;

import java.util.ArrayList;

/**
 * Created by moham on 10/21/2017.
 */

public class MoviesPresenterImpl implements MoviesPresenter {
    private MoviesView view;
    private static final int FAVOURITE_LIST_NAME = 0;
    private static final String PREF_KEY_INT = "sort_by";
    private static final int POPULAR_LIST_NAME = 1;
    private static final int TOP_VOTED_LIST_NAME = 2;
    private SharedPreferences sharedPreferences;
    private DBController controller;
    private ArrayList<Movie_ModelResults> favList=new ArrayList<>();
    private ArrayList<Movie_ModelResults> topList=new ArrayList<>();
    private ArrayList<Movie_ModelResults> popList=new ArrayList<>();
    private NetworkClass networkClass;

    public MoviesPresenterImpl(MoviesView view, SharedPreferences sharedPreferences, DBController controller) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.controller = controller;
        networkClass = new NetworkClass();
    }


    @Override
    public void loadTopMovies(LoadInterfaceListener listener) {
        if (topList.isEmpty()) {
            networkClass.getTopMovies(listener);
        } else
            listener.loadedSuccessfully(topList);
    }

    @Override
    public void loadPopMovies(LoadInterfaceListener listener) {
        if (popList.isEmpty()) {
            networkClass.getPopMovies(listener);
        } else
            listener.loadedSuccessfully(popList);
    }

    @Override
    public void loadFavMovies(LoadInterfaceListener listener) {
        if (favList.isEmpty()) {
            controller.get_all_fav_movies(listener);
        } else
            listener.loadedSuccessfully(favList);
    }

    @Override
    public void fetchMovies(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            switch (Utitlity.listName) {
                case FAVOURITE_LIST_NAME:
                    view.loadFavMovies(favList);
                    break;
                case POPULAR_LIST_NAME:
                    view.loadPopMovies(popList);
                    break;
                case TOP_VOTED_LIST_NAME:
                    view.loadTopMovies(topList);
                    break;
            }

        } else {
            switch (sharedPreferences.getInt(PREF_KEY_INT, 3)) {
                case FAVOURITE_LIST_NAME:
                    loadFavMovies(new LoadInterfaceListener() {
                        @Override
                        public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                            favList = list;
                            view.loadFavMovies(list);
                        }

                        @Override
                        public void error(String errorMsg) {
                            view.onLoadFailure(errorMsg);
                        }
                    });
                    break;
                case POPULAR_LIST_NAME:
                    loadPopMovies(new LoadInterfaceListener() {
                        @Override
                        public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                            popList = list;
                            view.loadPopMovies(list);
                        }

                        @Override
                        public void error(String errorMsg) {
                            view.onLoadFailure(errorMsg);

                        }
                    });
                    break;
                case TOP_VOTED_LIST_NAME:
                    loadTopMovies(new LoadInterfaceListener() {
                        @Override
                        public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                            view.loadTopMovies(list);
                            topList = list;
                        }

                        @Override
                        public void error(String errorMsg) {
                            view.onLoadFailure(errorMsg);

                        }
                    });
                    break;
                default:
                    loadPopMovies(new LoadInterfaceListener() {
                        @Override
                        public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                            view.loadPopMovies(list);

                        }

                        @Override
                        public void error(String errorMsg) {
                            view.onLoadFailure(errorMsg);

                        }
                    });
            }
        }
    }

    @Override
    public void saveChanges(int listName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_KEY_INT, listName);
        editor.apply();
        Utitlity.listName = listName;
    }


}
