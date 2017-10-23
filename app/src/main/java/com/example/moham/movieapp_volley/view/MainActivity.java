package com.example.moham.movieapp_volley.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.Utitlity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container2) != null) {
            Utitlity.mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container2, new DetailFragment()).commit();
            }
        } else {
           Utitlity.mTwoPane = false;
        }

    }


}