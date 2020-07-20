package com.example.movieapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.BuildConfig;
import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.repository.MovieRepository;

public class MovieViewModel extends ViewModel {
    private static final String TAG = ":: MovieViewModel :";
    
    private MovieRepository mMovieRepository;
    private MutableLiveData<MovieResult> mLiveMoviePopular;
    private MutableLiveData<MovieResult> mListTop;
    
    
    public void initPopular() {
        if (mLiveMoviePopular != null) return;
        mMovieRepository = MovieRepository.getInstance();// create Retrofit
        mLiveMoviePopular = mMovieRepository.getPopular(BuildConfig.API_KEY);
        Log.d(TAG, "initPopular called():  -> API_KEY" + BuildConfig.API_KEY);
    }
    
    public LiveData<MovieResult> getMoviePopular() {
        return mLiveMoviePopular;
    }
    
    public void initTopRated() {
        if (mListTop != null)
            return;
        mMovieRepository = MovieRepository.getInstance();
        mListTop = mMovieRepository.getTopRatedMovie(BuildConfig.API_KEY);
    }
    
    public MutableLiveData<MovieResult> getListTop() {
        return mListTop;
    }
}
