package com.example.moham.movieapp_volley.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.model.Trailer_ModelResults;
import com.example.moham.movieapp_volley.view.detail_view.WebActivity;

import java.util.ArrayList;

/**
 * Created by moham on 10/23/2017.
 */

public class WebRecyclerAdapter extends RecyclerView.Adapter<WebRecyclerAdapter.TrailerHolder> {
    private ArrayList<Trailer_ModelResults> list;
    private Context context;

    public WebRecyclerAdapter(ArrayList<Trailer_ModelResults> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailer_row, parent, false);
        return new TrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, int position) {
        String name = list.get(position).getName();
        holder.button.setText(name);
    }

    @Override
    public int getItemCount() {
        Log.d("yyyyy", list.size() + "");
        return list.size();
    }

    class TrailerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button;

        TrailerHolder(View itemView) {
            super(itemView);
            this.button = itemView.findViewById(R.id.btn_tr);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String key = list.get(getAdapterPosition()).getKey();
            String url="https://www.youtube.com/watch?v="+key;
            ((WebActivity)context).onItemClicked(url);
        }
    }
}
