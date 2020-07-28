package com.example.movieapp;

import android.os.Handler;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Automove {
    public <T> List<T> automatic(List<T> list, RecyclerView recyclerView, Runnable runnable,
                                 Handler handler, int timeDuration) {
        runnable = new Runnable() {
            int count = 0;
            
            @Override
            public void run() {
                if (count < list.size()) {
                    recyclerView.scrollToPosition(count++);
                    handler.postDelayed(this, timeDuration);
                } else if (count == list.size())
                    count = 0;
            }
        };
        handler.postDelayed(runnable, timeDuration);
        return list;
        
    }
}
