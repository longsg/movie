package com.example.movieapp;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.movieapp.view.MovieDetailFragment;

public class MovieDetailActivity extends SingleActivity {
    public static final String MOVIE_TAG = ":: MovieDetailActivity :";
    private static final String TAG = ":: MovieDetailActivity :";
    
    public static Intent movieDetailIntent(Context context, String id) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_TAG, id);
        return intent;
    }
    
    @Override
    protected Fragment createFragment() {
        return MovieDetailFragment.newInstance();
    }
}
