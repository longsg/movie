package com.example.movieapp.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movieapp.MovieDetailActivity;
import com.example.movieapp.PeopleDetailActivity;
import com.example.movieapp.R;
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

public class HomeFragment extends Fragment {
    private static final int          SPEED_SCROOL = 2000;
    private static final String       TAG          = ":: HomeFragment :";
    private              RecyclerView mPopularRecycler, mTopRecycler, mCastingRecycler;
    private ViewPager2      mViewPager2;
    private Toolbar         mToolbar;
    private TextView        mTextView;
    private MoviesAdapter   mMoviesAdapter;
    private SlideAdapter    mSlideAdapter;
    private MovieViewModel  mMovieViewModel;
    private Timer           mTimer;
    private PersonViewModel mPersonViewModel;
    private PeopleAdapter   mPeopleAdapter;
    private Handler         mHandler;
    private Runnable        mRunnable;
    
    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_activity, container, false);
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        // link @{ https://stackoverflow.com/questions/38189198/how-to-use-setsupportactionbar-in
        // -fragment}
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    
    
        // setSupportActionBar(mToolbar);
    
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        //movie
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mMovieViewModel.initPopular();
        mMovieViewModel.getMoviePopular().observe(this, this::getMoviePopular);
        //list top movie
        mMovieViewModel.initTopRated();
        mMovieViewModel.getListTop().observe(this, this::getTopRated);
        
        //person
        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mPersonViewModel.initAllPeople();
        mPersonViewModel.getPeopleMutableLiveData().observe(this, this::getPeople);
        
        mHandler = new Handler();
        
    }
    
    private void getTopRated(MovieResult movieResult) {
        List<Movie> movieList = movieResult.getResultsMovies();
        mMoviesAdapter = new MoviesAdapter(getActivity(), movieList);
        mTopRecycler.setHasFixedSize(true);
        mTopRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL, false));
        mTopRecycler.setAdapter(mMoviesAdapter);
        //detail movie
    
        mMoviesAdapter.setIClickListener((view, postion) -> {
            Intent intent = MovieDetailActivity.movieDetailIntent(getActivity(),
                    movieList.get(postion).getId());
            getActivity().startActivity(intent);
        });
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
        mCastingRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL, false));
    
        List<Persons> mPersonsList = new ArrayList<>(people.getPersonsList());
        mPeopleAdapter = new PeopleAdapter(getActivity(), mPersonsList);
        mPeopleAdapter.setIClickListener((view, position) -> {
        
            Log.d(TAG,
                    "getPeople called():  ->Info : " + mPersonsList.get(position).getBiography());
            Intent intent = PeopleDetailActivity.newIntent(getActivity(),
                    mPersonsList.get(position).getId());
            Objects.requireNonNull(getActivity()).startActivity(intent);
            assert getFragmentManager() != null;
            getFragmentManager().addOnBackStackChangedListener(null);
        
        
        });
        mCastingRecycler.setAdapter(mPeopleAdapter);
    
    
        automaticMove(mPersonsList, mCastingRecycler);
    }
    
    private void initView(View view) {
        mPopularRecycler = view.findViewById(R.id.recyclerView_popular);
        mTopRecycler = view.findViewById(R.id.recyclerView_topRated);
        mCastingRecycler = view.findViewById(R.id.recyclerView_caster);
        mViewPager2 = view.findViewById(R.id.viewpager2_container);
        mToolbar = view.findViewById(R.id.toolBar);
    }
    
    private void getMoviePopular(MovieResult movieResult) {
    
        List<Movie> movieList = new ArrayList<>(movieResult.getResultsMovies());
        for (Movie movie : movieList) {
            Log.d(TAG, "getMoviePopular called():  ->" + movie.getTitle() + "\n");
        }
        mMoviesAdapter = new MoviesAdapter(getActivity(), movieList);
        mPopularRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL, false));
        mPopularRecycler.setHasFixedSize(true);
        mPopularRecycler.setAdapter(mMoviesAdapter);
    
        mMoviesAdapter.setIClickListener((view, position) -> {
            Intent intent = MovieDetailActivity.movieDetailIntent(getActivity(),
                    movieList.get(position).getId());
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
        automaticMove(movieList, mPopularRecycler);
        slideView(movieList);
    }
    
    private void slideView(List<Movie> movieList) {
        List<Movie> mSlideMovie = new ArrayList<>();
        int         i           = 0;
        while (i < 5) {
            mSlideMovie.add(movieList.get(i));
            i++;
        }
        Log.d(TAG, "slideView called():  ->" + mSlideMovie.size());
    
        mSlideAdapter = new SlideAdapter(mSlideMovie, getActivity());
        mViewPager2.setAdapter(mSlideAdapter);
        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mViewPager2.post(() -> mViewPager2.setCurrentItem((mViewPager2.getCurrentItem() + 1) % mSlideMovie.size()));
            }
        };
        mTimer = new Timer();
        mTimer.schedule(timerTask, 5000, 2000);
    }
    //make a bullet
    
}

