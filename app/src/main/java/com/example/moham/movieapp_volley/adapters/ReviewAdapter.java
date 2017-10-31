package com.example.moham.movieapp_volley.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.model.Review_ModelResults;
import com.example.moham.movieapp_volley.view.detail_view.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by moham on 10/23/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewrHolder> {
    private ArrayList<Review_ModelResults> list;

    public ReviewAdapter(ArrayList<Review_ModelResults> list) {
        this.list = list;
    }

    @Override
    public ReviewrHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row, parent,false);
        return new ReviewrHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ReviewrHolder holder, int position) {
       holder.textView.setText("Author : "+list.get(position).getAuthor());
       holder.desc_text.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReviewrHolder extends RecyclerView.ViewHolder {
       TextView textView;
       ExpandableTextView desc_text;
        public ReviewrHolder(View itemView) {
            super(itemView);
           textView=itemView.findViewById(R.id.review_text);
           desc_text=itemView.findViewById(R.id.expandable_reveiw_txt);
        }

        }
}
