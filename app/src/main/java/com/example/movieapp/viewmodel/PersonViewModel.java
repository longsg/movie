package com.example.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.BuildConfig;
import com.example.movieapp.model.personmodel.People;
import com.example.movieapp.model.personmodel.Persons;
import com.example.movieapp.repository.PersonRepository;

public class PersonViewModel extends ViewModel {
    private static final String                   TAG = ":: PersonViewModel :";
    private              PersonRepository         mPersonRepository;
    private              MutableLiveData<People>  mPeopleMutableLiveData;
    private              MutableLiveData<Persons> mPersonsMutableLiveData;
    
    public void initAllPeople() {
        if (mPeopleMutableLiveData != null)
            return;
        mPersonRepository = PersonRepository.getInstanceRepository();
        mPeopleMutableLiveData = mPersonRepository.getAllPerson(BuildConfig.API_KEY);
    }
    
    public void initFindMovieCredits(String personId, String api) {
        if (mPersonsMutableLiveData != null)
            return;
        mPersonRepository = PersonRepository.getInstanceRepository();
        mPersonsMutableLiveData = mPersonRepository.getMovieCreditsPerson(personId, api);
    }
    
    
    public LiveData<Persons> findMovieCredits() {
        return mPersonsMutableLiveData;
    }
    
    public LiveData<People> getPeopleMutableLiveData() {
        return mPeopleMutableLiveData;
    }
}
