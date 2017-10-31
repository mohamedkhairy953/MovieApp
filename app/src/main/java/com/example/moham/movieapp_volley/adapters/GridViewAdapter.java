package com.example.moham.movieapp_volley.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by moham on 8/12/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie_ModelResults> movieList;

    public GridViewAdapter(Context context, ArrayList<Movie_ModelResults> movieDbList) {
        this.context = context;
        this.movieList = movieDbList;
    }

    public void addArrayList(ArrayList<Movie_ModelResults> l) {
        this.movieList.clear();
        this.movieList = l;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Movie_ModelResults getItem(int position) {
        return movieList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 123456000 + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_item_row, parent, false);
        }
        Movie_ModelResults movieDb = getItem(position);


        ImageView imageViewcustom =  convertView.findViewById(R.id.customImageView);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + movieDb.getPoster_path())
                .placeholder(R.drawable.poster_place_holder)
                .into(imageViewcustom);

        return convertView;
    }
}

