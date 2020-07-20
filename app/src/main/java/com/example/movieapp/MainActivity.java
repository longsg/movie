package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.movieapp.adapter.SlideAdapter;
import com.example.movieapp.adapter.movieadapter.MoviesAdapter;
import com.example.movieapp.adapter.peopleadapter.PeopleAdapter;
import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.model.moviemodel.MovieResult;
import com.example.movieapp.model.personmodel.People;
import com.example.movieapp.model.personmodel.Persons;
import com.example.movieapp.viewmodel.MovieViewModel;
import com.example.movieapp.viewmodel.PersonViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = ":: MainActivity :";
    private static final int SPEED_SCROOL = 2000;
    private RecyclerView mPopularRecycler, mTopRecycler, mCastingRecycler;
    private ViewPager2 mViewPager2;
    private Toolbar mToolbar;
    private TextView mTextView;
    private MoviesAdapter mMoviesAdapter;
    private SlideAdapter mSlideAdapter;
    private MovieViewModel mMovieViewModel;
    private Timer mTimer;
    private PersonViewModel mPersonViewModel;
    private PeopleAdapter mPeopleAdapter;
    private Handler mHandler;
    private Runnable mRunnable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        //movie
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mMovieViewModel.initPopular();
        mMovieViewModel.getMoviePopular().observe(this, this::getMoviePopular);
        //list top movie
        mMovieViewModel.initTopRated();
        ;
        mMovieViewModel.getListTop().observe(this, this::getTopRated);
        
        //person
        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mPersonViewModel.initAllPeople();
        mPersonViewModel.getPeopleMutableLiveData().observe(this, this::getPeople);
        
        mHandler = new Handler();
        
    }
    
    private void getTopRated(MovieResult movieResult) {
        List<Movie> movieList = movieResult.getResultsMovies();
        mMoviesAdapter = new MoviesAdapter(this, movieList);
        mTopRecycler.setHasFixedSize(true);
        mTopRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        
        mTopRecycler.setAdapter(mMoviesAdapter);
        automaticMove(movieList, mTopRecycler);
    }
    
    private <T> List<T> automaticMove(List<T> list, RecyclerView recyclerView) {
        
        mRunnable = new Runnable() {
            int count = 0;
            
            @Override
            public void run() {
                if (count < list.size()) {
                    recyclerView.scrollToPosition(count++);
                    mHandler.postDelayed(this, SPEED_SCROOL);
                } else if (count == list.size()) {
                    count = 0;
                }
            }
        };
        mHandler.postDelayed(mRunnable, SPEED_SCROOL);
        return list;
    }
    
    private void getPeople(People people) {
        
        mCastingRecycler.setHasFixedSize(true);
        mCastingRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        
        List<Persons> mPersonsList = new ArrayList<>(people.getPersonsList());
        
        mPeopleAdapter = new PeopleAdapter(this, mPersonsList);
        
        mCastingRecycler.setAdapter(mPeopleAdapter);
        automaticMove(mPersonsList, mCastingRecycler);
    }
    
    private void initView() {
        mPopularRecycler = findViewById(R.id.recyclerView_popular);
        mTopRecycler = findViewById(R.id.recyclerView_topRated);
        mCastingRecycler = findViewById(R.id.recyclerView_caster);
        mViewPager2 = findViewById(R.id.viewpager2_container);
        mToolbar = findViewById(R.id.toolBar);
    }
    
    private void getMoviePopular(MovieResult movieResult) {
        
        List<Movie> movieList = new ArrayList<>(movieResult.getResultsMovies());
        for (Movie movie : movieList) {
            Log.d(TAG, "getMoviePopular called():  ->" + movie.getTitle() + "\n");
        }
        mMoviesAdapter = new MoviesAdapter(this, movieList);
        mPopularRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mPopularRecycler.setHasFixedSize(true);
        mPopularRecycler.setAdapter(mMoviesAdapter);
        automaticMove(movieList, mPopularRecycler);
        slideView(movieList);
    }
    
    private void slideView(List<Movie> movieList) {
        List<Movie> mSlideMovie = new ArrayList<>();
        int i = 0;
        while (i < 5) {
            mSlideMovie.add(movieList.get(i));
            i++;
        }
        Log.d(TAG, "slideView called():  ->" + mSlideMovie.size());
        
        mSlideAdapter = new SlideAdapter(mSlideMovie, this);
        mViewPager2.setAdapter(mSlideAdapter);
        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mViewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        mViewPager2.setCurrentItem((mViewPager2.getCurrentItem() + 1) % mSlideMovie.size());
                    }
                });
            }
        };
        mTimer = new Timer();
        mTimer.schedule(timerTask, 5000, 2000);
    }
    //make a bullet
    
}