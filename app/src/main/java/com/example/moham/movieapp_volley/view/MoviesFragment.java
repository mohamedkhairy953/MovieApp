package com.example.moham.movieapp_volley.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.moham.movieapp_volley.DBController;
import com.example.moham.movieapp_volley.GridViewAdapter;
import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.Utitlity;
import com.example.moham.movieapp_volley.model.Movie_ModelResults;
import com.example.moham.movieapp_volley.presenter.LoadInterfaceListener;
import com.example.moham.movieapp_volley.presenter.MoviesPresenterImpl;

import java.util.ArrayList;

/**
 * Created by moham on 8/12/2016.
 */
public class MoviesFragment extends Fragment implements MoviesView {
    private static final String API_KEY = "faa38d8564f66b5c1339d257bf6a6da9";
    private static final int FAVOURITE_LIST_NAME = 0;
    private static final String PREF_KEY_INT = "sort_by";
    private static final int POPULAR_LIST_NAME = 1;
    private static final int TOP_VOTED_LIST_NAME = 2;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    private ArrayList<Movie_ModelResults> topVoted_arrylist = new ArrayList<>();
    private ArrayList<Movie_ModelResults> Popular_arrylist = new ArrayList<>();
    MoviesPresenterImpl presenter;


    public MoviesFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MoviesPresenterImpl(this, Utitlity.getSharedPref(getActivity()), new DBController(getContext()));
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.main_fragment, container, false);
        gridView = root_view.findViewById(R.id.topMovieGrid);
        gridViewAdapter = new GridViewAdapter(getContext(), new ArrayList<Movie_ModelResults>());
        gridView.setAdapter(gridViewAdapter);
        presenter.fetchMovies(savedInstanceState);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie_ModelResults movie = (Movie_ModelResults) parent.getAdapter().getItem(position);
                if (Utitlity.mTwoPane) {
                    Bundle arg = new Bundle();
                    arg.putSerializable("movie", movie);
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(arg);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container2, detailFragment).commit();
                } else {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("movie", movie);
                    startActivity(intent);
                }
            }
        });
        return root_view;
    }

    private void loadAdapter(ArrayList<Movie_ModelResults> list, String activityTitle, int listName) {
        gridViewAdapter.addArrayList(list);
        getActivity().setTitle(activityTitle);
        presenter.saveChanges(listName);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_detail, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_pop_id) {
            presenter.loadPopMovies(new LoadInterfaceListener() {
                @Override
                public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                    loadPopMovies(list);
                }

                @Override
                public void error(String errorMsg) {
                    onLoadFailure(errorMsg);
                }
            });
        } else if (id == R.id.sort_vote_id) {
            presenter.loadTopMovies(new LoadInterfaceListener() {
                @Override
                public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                    loadTopMovies(list);
                }

                @Override
                public void error(String errorMsg) {
                    onLoadFailure(errorMsg);
                }
            });
        } else {
            presenter.loadFavMovies(new LoadInterfaceListener() {
                @Override
                public void loadedSuccessfully(ArrayList<Movie_ModelResults> list) {
                    loadFavMovies(list);
                }

                @Override
                public void error(String errorMsg) {
                    onLoadFailure(errorMsg);
                }
            });
        }

        return true;
    }


    @Override
    public void onLoadFailure(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        Log.d("SSS",errorMsg);

    }

    @Override
    public void loadTopMovies(ArrayList<Movie_ModelResults> topList) {
        Toast.makeText(getContext(), "tt", Toast.LENGTH_SHORT).show();
        loadAdapter(topList, "TopVoted Movies", TOP_VOTED_LIST_NAME);

    }

    @Override
    public void loadPopMovies(ArrayList<Movie_ModelResults> popList) {
        Toast.makeText(getContext(), "pp", Toast.LENGTH_SHORT).show();
        loadAdapter(popList, "Popular Movies", POPULAR_LIST_NAME);
    }

    @Override
    public void loadFavMovies(ArrayList<Movie_ModelResults> favList) {
        loadAdapter(favList, "Favourite Movies", FAVOURITE_LIST_NAME);
    }
}
