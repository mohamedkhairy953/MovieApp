package com.example.moham.movieapp_volley.view.detail_view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.moham.movieapp_volley.contractor.DBController;
import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.adapters.ReviewAdapter;
import com.example.moham.movieapp_volley.adapters.TrailerAdapter;
import com.example.moham.movieapp_volley.databinding.DetailFragmentBinding;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.model.Review_ModelResults;
import com.example.moham.movieapp_volley.model.Trailer_ModelResults;
import com.example.moham.movieapp_volley.presenter.details.DeatailsPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by moham on 8/12/2016.
 */
public class DetailFragment extends Fragment implements DetailsView {
    DBController dbController;
    private RecyclerView listView_trailers;
    private RecyclerView listView_reviews;
    Movie_ModelResults movie_intent;
    Intent intent;
    private DeatailsPresenterImpl presenter;
    DetailFragmentBinding binding;


    public DetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbController = new DBController(getContext());
        intent = getActivity().getIntent();
        presenter = new DeatailsPresenterImpl(this, dbController);

        if (getArguments() != null || intent.getSerializableExtra("movie") != null) {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.detail_fragment, container, false);
            presenter.doInitializeMovieIntent();
            bindContents();
            binding.addFavButtonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.doInsertMovieInFav(movie_intent);
                }
            });

            return binding.getRoot();
        } else {
            return null;
        }
    }

    private void bindContents() {
        getActivity().setTitle(movie_intent.getTitle());
        binding.detailReleaseDate.setText(movie_intent.getRelease_date());
        binding.detailOverview.setText(movie_intent.getOverview());
        binding.detailVoteCount.setText(movie_intent.getVote_count() + "");
        binding.detailPopularity.setText(movie_intent.getPopularity() + "");
        Picasso.with(getActivity()).load("https://image.tmdb.org/t/p/w185" + movie_intent.getPoster_path())
                .placeholder(R.drawable.poster_place_holder)
                .into(binding.detailImage);
        presenter.doFavBtnChange(movie_intent.getId());

        listView_trailers = binding.trailersListId;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listView_trailers.setLayoutManager(layoutManager);
        presenter.doLoadTrailers(movie_intent.getId());

        listView_reviews = binding.reviewsListId;
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        listView_reviews.setLayoutManager(layoutManager2);
        presenter.doLoadReviews(movie_intent.getId());


    }


    @Override
    public void initializeMovieIntentFromFragment() {
        movie_intent = (Movie_ModelResults) getArguments().getSerializable("movie");

    }

    @Override
    public void initializeMovieIntentFromActivity() {
        movie_intent = (Movie_ModelResults) intent.getSerializableExtra("movie");
    }

    @Override
    public void favBtnDisabling() {
        binding.addFavButtonId.setEnabled(false);
        binding.addFavButtonId.setTextColor(Color.GRAY);
    }

    @Override
    public void favBtnEnabling() {
        binding.addFavButtonId.setEnabled(true);
        binding.addFavButtonId.setTextColor(Color.RED);
    }

    @Override
    public void showConfirmToast(Movie_ModelResults movieIntent) {
        Toast.makeText(getActivity(), movieIntent.getOriginal_title() + " has been added to your favourits", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTrailerLoaded(ArrayList<Trailer_ModelResults> trailers) {
        TrailerAdapter adapter_trailers = new TrailerAdapter(trailers,getActivity());
        listView_trailers.setAdapter(adapter_trailers);
    }

    @Override
    public void onLoadFailure(String s) {
//        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReviewLoaded(ArrayList<Review_ModelResults> reviews) {
        ReviewAdapter adapter_review = new ReviewAdapter(reviews);
        listView_reviews.setAdapter(adapter_review);
    }
}

