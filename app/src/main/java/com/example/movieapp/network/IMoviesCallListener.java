package com.example.movieapp.network;

import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.model.personmodel.People;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMoviesCallListener {
    @GET("/3/movie/popular")
//movie
    Call<MovieResult> getPopularMovie(@Query("api_key") String api);
    
    @GET("/3/movie/top_rated")
    Call<MovieResult> getTopRatedMovie(@Query("api_key") String api);
    
    //people
    @GET("/3/person/popular")
    Call<People> getPeoplePopular(@Query("api_key") String api);
    
}
