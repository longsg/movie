package com.example.movieapp.view.people;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.BuildConfig;
import com.example.movieapp.MovieDetailActivity;
import com.example.movieapp.PeopleDetailActivity;
import com.example.movieapp.R;
import com.example.movieapp.adapter.movieadapter.CastFilmAdapter;
import com.example.movieapp.model.personmodel.Cast;
import com.example.movieapp.model.personmodel.CastFilm;
import com.example.movieapp.model.personmodel.Persons;
import com.example.movieapp.network.MovieRetrofit;
import com.example.movieapp.network.UrlManager;
import com.example.movieapp.repository.IMoviesCallListener;
import com.example.movieapp.viewmodel.PersonViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleDetailFragment extends Fragment {
    private static final String   TAG = "::PeopleDetailFragment:";
    private              TextView mName, mBio, mPlace, mBirth;
    private RecyclerView        mRecyclerViewCredits;
    private ImageView           mImagePerson;
    private PersonViewModel     mPersonViewModel;
    private String              mId;
    private IMoviesCallListener mIMoviesCallListener;
    private CastFilmAdapter     mCastFilmAdapter;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPersonViewModel =
                ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(PersonViewModel.class);
        if (getActivity().getIntent() != null) {
            mId = (String) Objects.requireNonNull(getActivity().getIntent().getExtras()).getString(PeopleDetailActivity.PERSON_ID);
        }
        
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.people_detail_fragment, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createView(view);
        getDetailPerson(mId);
        mPersonViewModel.initFindMovieCredits(mId, BuildConfig.API_KEY);
        mPersonViewModel.findMovieCredits().observe(Objects.requireNonNull(getActivity()),
                this::findMovieCredits);
    }
    
    private void getDetailPerson(String id) {
        mIMoviesCallListener = MovieRetrofit.createRetrofit(IMoviesCallListener.class);
        mIMoviesCallListener.getPersonDetail(id, BuildConfig.API_KEY).enqueue(new Callback<Persons>() {
            @Override
            public void onResponse(Call<Persons> call, Response<Persons> response) {
                if (response.isSuccessful()) {
                    Persons persons = response.body();
                    //  assert persons != null;
                    Log.d(TAG, "onResponse called(): DataPerson  -> " + persons.getCastFilms());
                    onBindPeoPle(persons);
                    Log.d(TAG, "onResponse called():  -> Detail " + response.raw());
                    if (!persons.getBiography().isEmpty() && !persons.getProfile_path().isEmpty()) {
                        Glide.with(Objects.requireNonNull(getActivity()))
                                .load(UrlManager.POSTER_URL + persons.getProfile_path())
                                .centerCrop()
                                .into(mImagePerson);
                        mName.setText(persons.getName());
                        mBio.setText(persons.getBiography());
                        mPlace.setText(persons.getPlace_of_birth());
                        mBirth.setText(persons.getBirthday());
                    }
                    
                }
            }
            
            @Override
            public void onFailure(Call<Persons> call, Throwable t) {
                Log.d(TAG, "onFailure called():  -> " + t.getMessage());
            }
        });
    }
    
    private void onBindPeoPle(Persons persons) {
        Log.d(TAG, "onBindPeoPle called():  -> " + persons.getBiography());
    }
    
    private void findMovieCredits(CastFilm castFilm) {
        List<Cast> castList = new ArrayList<>(castFilm.getCasts());
        for (Cast cast : castList) {
            Log.d(TAG,
                    "findMovieCredits called():  -> Item  " + cast.getTitle() + "\n" + cast.getOverview());
        }
        mCastFilmAdapter = new CastFilmAdapter(castList, getActivity());
        mRecyclerViewCredits.setAdapter(mCastFilmAdapter);
        mCastFilmAdapter.setIClickListener((view, position) -> {
            Intent intent = MovieDetailActivity.movieDetailIntent(getActivity(),
                    String.valueOf(castList.get(position).getId()));
            Objects.requireNonNull(getActivity()).startActivity(intent);
        });
    }
    
    private void createView(View view) {
        mImagePerson = view.findViewById(R.id.image_person_poster);
        mBio = view.findViewById(R.id.person_bio_text);
        // @{link https://stackoverflow.com/questions/1748977/making-textview-scrollable-on-android}
        mBio.setMovementMethod(new ScrollingMovementMethod());
        mBirth = view.findViewById(R.id.person_age_text);
        mPlace = view.findViewById(R.id.place_born_text);
        mName = view.findViewById(R.id.name_person_text);
        mRecyclerViewCredits = view.findViewById(R.id.recyclerView_credits);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerViewCredits.setLayoutManager(gridLayoutManager);
        mRecyclerViewCredits.setHasFixedSize(true);
        
        
    }
}
