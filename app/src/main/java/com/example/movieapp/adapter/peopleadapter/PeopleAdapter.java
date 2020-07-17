package com.example.movieapp.adapter.peopleadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;

import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    private Context mContext;
    
    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
    
    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.onBindPeople();
    }
    
    @Override
    public int getItemCount() {
        return 0;
    }
    
    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        
        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }
        
        public void onBindPeople() {
        }
        
        @Override
        public void onClick(View view) {
        
        }
    }
    
}
