package com.example.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.BuildConfig;
import com.example.movieapp.model.personmodel.People;
import com.example.movieapp.repository.PersonRepository;

public class PersonViewModel extends ViewModel {
    private static final String TAG = ":: PersonViewModel :";
    private PersonRepository mPersonRepository;
    private MutableLiveData<People> mPeopleMutableLiveData;
    
    public void initAllPeople() {
        if (mPeopleMutableLiveData != null)
            return;
        mPersonRepository = PersonRepository.getInstanceRepository();
        mPeopleMutableLiveData = mPersonRepository.getAllPerson(BuildConfig.API_KEY);
    }
    
    public LiveData<People> getPeopleMutableLiveData() {
        return mPeopleMutableLiveData;
    }
}
