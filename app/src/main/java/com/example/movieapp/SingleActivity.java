package com.example.movieapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_main_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        
        Fragment fragment = fragmentManager.findFragmentById(R.id.frameLayout_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout_container, fragment)
                    .commit();
        }
        
    }
}
