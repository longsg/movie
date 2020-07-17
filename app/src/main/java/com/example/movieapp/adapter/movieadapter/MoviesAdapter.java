package com.example.movieapp.adapter.movieadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.network.IClickListener;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private IClickListener mIClickListener;
    
    public void setIClickListener(IClickListener IClickListener) {
        mIClickListener = IClickListener;
    }
    
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager2_header_image, parent, false);
        return new MovieViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    
    }
    
    @Override
    public int getItemCount() {
        return 0;
    }
    
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTitle;
        
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_viewpager2);
            mTitle = itemView.findViewById(R.id.title_viewpager2_film);
            
            itemView.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View view) {
            mIClickListener.onClick(view, getAdapterPosition());
        }
    }
}
