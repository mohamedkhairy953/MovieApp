package com.example.moham.movieapp_volley.view.main_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.contractor.SharedPref;
import com.example.moham.movieapp_volley.view.detail_view.DetailFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container2) != null) {
            SharedPref.mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container2, new DetailFragment()).commit();
            }
        } else {
           SharedPref.mTwoPane = false;
        }

    }


}