package com.example.moham.movieapp_volley.view.detail_view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.moham.movieapp_volley.R;
import com.example.moham.movieapp_volley.adapters.WebRecyclerAdapter;
import com.example.moham.movieapp_volley.databinding.ActivityWebBinding;
import com.example.moham.movieapp_volley.model.Trailer_ModelResults;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {
    String url = "";
    ArrayList<Trailer_ModelResults> list = new ArrayList<>();
    ActivityWebBinding binding;
    WebRecyclerAdapter adapter;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            list = intent.getParcelableArrayListExtra("list");
        }
        WebView webview = binding.webview;
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.webviewRecycler.setLayoutManager(layoutManager);
        adapter = new WebRecyclerAdapter(list, this);
        binding.webviewRecycler.setAdapter(adapter);
    }
   public void onItemClicked(String url){
        binding.webview.loadUrl(url);
    }
}
