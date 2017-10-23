package com.example.moham.movieapp_volley.presenter;

import com.example.moham.movieapp_volley.model.Movie_Model;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;

import java.util.ArrayList;

/**
 * Created by moham on 10/22/2017.
 */

public interface LoadInterfaceListener {
    void loadedSuccessfully(ArrayList<Movie_ModelResults> list);
    void error(String errorMsg);
}
