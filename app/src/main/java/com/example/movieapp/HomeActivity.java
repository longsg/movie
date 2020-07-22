package com.example.movieapp;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.movieapp.view.HomeFragment;

public class HomeActivity extends SingleActivity {
    public static final String MOVIE_TAG = ":: HomeActivity :";
    private static final String TAG = ":: MainActivity :";
    
    public static Intent newInstance(Context contextPakage) {
        Intent intent = new Intent(contextPakage, HomeActivity.class);
        return intent;
    }
    
    @Override
    protected Fragment createFragment() {
        return new HomeFragment();
    }
}