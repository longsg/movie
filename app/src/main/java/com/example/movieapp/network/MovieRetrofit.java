package com.example.movieapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofit {
    private static Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(UrlManager.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
    public static <S> S createRetrofit(Class<S> classService) {
        return sRetrofit.create(classService);
    }
}
