package com.example.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.network.MovieRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final String              TAG = ":: MovieRepository :";
    private static       MovieRepository     sMovieRepository;
    private              IMoviesCallListener mIMoviesCallListener;
    
    public MovieRepository() {
        mIMoviesCallListener = MovieRetrofit.createRetrofit(IMoviesCallListener.class);
    }
    
    public static MovieRepository getInstance() {
        if (sMovieRepository == null)
            sMovieRepository = new MovieRepository();
        Log.d(TAG, "getInstance called():  -> " + sMovieRepository.toString());
        return sMovieRepository;
    }
    
    public MutableLiveData<MovieResult> getPopular(String api) {
        final MutableLiveData<MovieResult> movieMutableLiveData = new MutableLiveData<>();
        mIMoviesCallListener.getPopularMovie(api).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse called():  ->" + response.body());
                    movieMutableLiveData.setValue(response.body());
                }
            }
    
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.d(TAG, "onFailure called():  ->" + t.getMessage());
                movieMutableLiveData.setValue(null);
            }
        });
        return movieMutableLiveData;
    }
    
    public MutableLiveData<MovieResult> getTopRatedMovie(String api) {
        MutableLiveData<MovieResult> movieResultMutableLiveData = new MutableLiveData<>();
        mIMoviesCallListener.getTopRatedMovie(api).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse called():  -> " + response.raw());
                    movieResultMutableLiveData.setValue(response.body());
                }
            }
    
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                movieResultMutableLiveData.setValue(null);
            }
        });
        return movieResultMutableLiveData;
    }
    
    public MutableLiveData<MovieResult> getSimilarMovie(String movieId, String api) {
        MutableLiveData<MovieResult> movieResultMutableLiveData = new MutableLiveData<>();
        
        mIMoviesCallListener.getSimilarMovie(movieId, api).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    movieResultMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse called():  ->URL " + response.body());
                }
            }
    
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                movieResultMutableLiveData.setValue(null);
            }
        });
        return movieResultMutableLiveData;
    
    }
    
    public MutableLiveData<MovieResult> findMovieWithKeyWords(String keyWords, String api) {
        MutableLiveData<MovieResult> movieResult = new MutableLiveData<>();
        mIMoviesCallListener.findMovieWithKeyWords(keyWords, api).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse called():  -> Find Movie URL " + response.raw());
                    movieResult.setValue(response.body());
                }
            }
            
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.d(TAG, "onFailure called():  -> Fail Url " + t.getMessage());
            }
        });
        return movieResult;
    }
    
}

