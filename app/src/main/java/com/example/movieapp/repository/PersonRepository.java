package com.example.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.personmodel.People;
import com.example.movieapp.network.MovieRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {
    private static final String TAG = ":: PersonRepository :";
    private static PersonRepository sPersonRepository;
    private MovieRetrofit mMovieRetrofit;
    private IMoviesCallListener mIMoviesCallListener;
    
    public PersonRepository() {
        mIMoviesCallListener = MovieRetrofit.createRetrofit(IMoviesCallListener.class);
    }
    
    public static PersonRepository getInstanceRepository() {
        if (sPersonRepository == null)
            sPersonRepository = new PersonRepository();
        return sPersonRepository;
    }
    
    public MutableLiveData<People> getAllPerson(String api) {
        MutableLiveData<People> mutableLiveData = new MutableLiveData<>();
        mIMoviesCallListener.getPeoplePopular(api)
                .enqueue(new Callback<People>() {
                    @Override
                    public void onResponse(Call<People> call, Response<People> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse called():  ->  " + response.raw());
                            mutableLiveData.setValue(response.body());
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<People> call, Throwable t) {
                        mutableLiveData.setValue(null);
                    }
                });
        return mutableLiveData;
    }
}
