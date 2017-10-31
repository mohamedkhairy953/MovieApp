package com.example.moham.movieapp_volley.presenter.details;

import com.example.moham.movieapp_volley.model.Trailer_ModelResults;

import java.util.ArrayList;

/**
 * Created by moham on 10/26/2017.
 */

public interface TrailerInterfaceListener {
    void success(ArrayList<Trailer_ModelResults> results);
    void error(String errorMsg);
}
