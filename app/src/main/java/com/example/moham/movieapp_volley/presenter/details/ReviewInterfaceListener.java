package com.example.moham.movieapp_volley.presenter.details;

import com.example.moham.movieapp_volley.model.Review_ModelResults;

import java.util.ArrayList;

/**
 * Created by moham on 10/26/2017.
 */

public interface ReviewInterfaceListener {
    void success(ArrayList<Review_ModelResults> results);
    void  error(String errMsg);
}
