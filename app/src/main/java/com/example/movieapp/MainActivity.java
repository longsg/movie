package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import android.os.Bundle;
import android.util.Log;

import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //  String API_KEY = BuildConfig.API_KEY;
    private MovieViewModel mMovieViewModel;
    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mMovieViewModel.initPopular();
        mMovieViewModel.getMoviePopular().observe(this, this::getMoviePopular);
    }
    
    private void getMoviePopular(MovieResult movieResult) {
        
        List<Movie> movieList = new ArrayList<>(movieResult.getResultsMovies());
        for (Movie movie : movieList) {
            Log.d(TAG, "getMoviePopular called():  ->" + movie.getTitle() + "\n");
        }
        
    }
}