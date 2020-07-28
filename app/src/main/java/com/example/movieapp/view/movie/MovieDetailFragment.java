package com.example.movieapp.view.movie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Automove;
import com.example.movieapp.BuildConfig;
import com.example.movieapp.MovieDetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.adapter.movieadapter.MoviesAdapter;
import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.network.MovieRetrofit;
import com.example.movieapp.network.UrlManager;
import com.example.movieapp.repository.IMoviesCallListener;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {
    private static final String        TAG = "MOVIE_ DETAIL_FRAGMENT";
    // TODO: Rename and change types of parameters
    private              MoviesAdapter mMoviesAdapter;
    private              ImageView     mImageHeader, mImagePoster;
    private TextView mTitle, mDateRelease,
            mTimeDuration, mPopularity,
            mRatingPoint, mOverView;
    private String         mId;
    private RecyclerView   mRecyclerSimilar;
    private MovieViewModel mMovieViewModel;
    private Automove       mAutomove;
    private Handler        mHandler;
    private Runnable       mRunnable;
    
    public MovieDetailFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance() {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle              args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    
    @SuppressLint("LongLogTag")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = (String) getActivity().getIntent().getExtras().get(MovieDetailActivity.MOVIE_TAG);
            Log.d(TAG, "onCreate called():  -> View" + mId);
            
        }
        Log.d(TAG, "onCreate called():  -> ID" + mId);
        mMovieViewModel =
                ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);
        mHandler = new Handler();
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createUI(view);
        getMovieDetail(mId);
        mMovieViewModel.initSimilarMovie(mId, BuildConfig.API_KEY);
        mMovieViewModel.getSimilarMovie().observe(Objects.requireNonNull(getActivity()),
                this::getSimilarMovie);
    }
    
    private void getSimilarMovie(MovieResult movieResult) {
        
        List<Movie> movieList = new ArrayList<>();
        movieList.addAll(movieResult.getResultsMovies());
        Log.d(TAG, "getSimilarMovie called():  -> SIZE " + movieList.size());
        mRecyclerSimilar.setHasFixedSize(true);
        mRecyclerSimilar.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getActivity(), androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false));
        
        mMoviesAdapter = new MoviesAdapter(getActivity(), movieList);
        mRecyclerSimilar.setAdapter(mMoviesAdapter);
        mAutomove = new Automove();
        mAutomove.automatic(movieList, mRecyclerSimilar, mRunnable, mHandler, 2000);
        
        mMoviesAdapter.setIClickListener((view, position) -> {
            Intent intent = MovieDetailActivity.movieDetailIntent(getActivity(),
                    movieList.get(position).getId());
            Objects.requireNonNull(getActivity()).startActivity(intent);
            getActivity().finish();
            
        });
        
        
    }
    
    private void getMovieDetail(String id) {
        IMoviesCallListener movieRetrofit = MovieRetrofit.createRetrofit(IMoviesCallListener.class);
        Call<Movie>         movieCall     = movieRetrofit.getDetailMovie(id, BuildConfig.API_KEY);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG,
                            "onResponse called():  ->" + response.raw() + "\n" + BuildConfig.API_KEY);
                    Movie movie = response.body();
                    Log.d(TAG, "onResponse called():  -> Object :" + movie.getTitle());
                    updateView(movie);
                }
            }
            
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            
            }
        });
    }
    
    @SuppressLint("SetTextI18n")
    private void updateView(Movie movie) {
        if (movie.getPoster_path() == null)
            mImagePoster.setImageResource(R.drawable.blade_poster);
        else if (movie.getBackgroud_path() == null) {
            mImageHeader.setImageResource(R.drawable.blade);
        } else {
            Glide.with(Objects.requireNonNull(getActivity()))
                    .load(UrlManager.POSTER_URL + movie.getPoster_path())
                    .centerCrop()
                    .into(mImagePoster);
            Glide.with(getActivity())
                    .load(UrlManager.HEADER_URL + movie.getBackgroud_path())
                    .centerCrop()
                    .into(mImageHeader);
        } mTitle.setText(movie.getTitle());
        mOverView.setText(movie.getOverview());
        mPopularity.setText(movie.getPopularity());
        mDateRelease.setText(movie.getRelease_date());
        mRatingPoint.setText(movie.getVote_average() + " ");
        mTimeDuration.setText(movie.getRuntime() + " min");
        
    }
    
    private void createUI(View view) {
        mImageHeader = view.findViewById(R.id.image_header_movie_detail);
        mImagePoster = view.findViewById(R.id.image_poster_movie_detail);
        mTitle = view.findViewById(R.id.title_movie_detail);
        mDateRelease = view.findViewById(R.id.release_date_detail_text);
        mTimeDuration = view.findViewById(R.id.runtime_text);
        mPopularity = view.findViewById(R.id.popularity_movie);
        mRatingPoint = view.findViewById(R.id.rating_point_text);
        mOverView = view.findViewById(R.id.overview_movie_text);
        mRecyclerSimilar = view.findViewById(R.id.recyclerView_similar);
    }
}