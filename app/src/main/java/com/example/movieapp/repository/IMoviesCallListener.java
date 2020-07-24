package com.example.movieapp.repository;

import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.model.personmodel.People;
import com.example.movieapp.model.personmodel.Persons;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMoviesCallListener {
    @GET("/3/movie/popular")
        //movie
    Call<MovieResult> getPopularMovie(@Query("api_key") String api);
    
    //toprated movie
    @GET("/3/movie/top_rated")
    Call<MovieResult> getTopRatedMovie(@Query("api_key") String api);
    
    //detail movie
    @GET("/3/movie/{movie_id}")
    Call<Movie> getDetailMovie(@Path("movie_id") String movieId,
                               @Query("api_key") String api);
    
    //similar movie
    @GET("/3/movie/{movie_id}/similar")
    Call<MovieResult> getSimilarMovie(@Path("movie_id") String movieId,
                                      @Query("api_key") String api);
    
    //people
    @GET("/3/person/popular")
    Call<People> getPeoplePopular(@Query("api_key") String api);
    
    // get movie credits
    @GET("/3/person/{person_id}/movie_credits")
    Call<Persons> getMovieCredits(@Path("person_id") String personId,
                                  @Query("api_key") String api_key);
    
    @GET("/3/person/{person_id}")
    Call<Persons> getPersonDetail(@Path("person_id") String personId,
                                  @Query("api_key") String apiKey);
    
}
