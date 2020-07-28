package com.example.movieapp;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.movieapp.view.people.PeopleDetailFragment;

public class PeopleDetailActivity extends SingleActivity {
    public static final  String PERSON_ID = "PERSON ID";
    private static final String TAG       = ":: PeopleDetailActivity :";
    
    public static Intent newIntent(Context contextPakage, String person) {
        Intent intent = new Intent(contextPakage, PeopleDetailActivity.class);
        intent.putExtra(PERSON_ID, person);
        return intent;
    }
    
    @Override
    protected Fragment createFragment() {
        return new PeopleDetailFragment();
    }
}
