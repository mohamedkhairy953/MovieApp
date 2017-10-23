package com.example.moham.movieapp_volley.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.view.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        try {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Toast.makeText(this, "home button exception", Toast.LENGTH_SHORT).show();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container2, new DetailFragment()).commit();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
