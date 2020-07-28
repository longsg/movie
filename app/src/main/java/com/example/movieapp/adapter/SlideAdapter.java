package com.example.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.network.UrlManager;

import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {
    private List<Movie> mMovieList;
    private Context     mContext;
    
    public SlideAdapter(@NonNull List<Movie> movieList, Context context) {
        mMovieList = movieList;
        mContext = context;
    }
    
    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager2_header_image, parent, false);
        return new SlideViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.onBindViewSlide(movie);
    }
    
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
    
    public class SlideViewHolder extends RecyclerView.ViewHolder {
        private Movie     mMovie;
        private ImageView mImageView;
        private TextView  mTitle;
        
        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_viewpager2);
            mTitle = itemView.findViewById(R.id.title_viewpager2_film);
        }
        
        public void onBindViewSlide(Movie movie) {
            mMovie = movie;
            if (!mMovie.getBackgroud_path().isEmpty() && !mMovie.getTitle().isEmpty()) {
                Glide.with(mContext)
                        .load(UrlManager.HEADER_URL + mMovie.getBackgroud_path())
                        .centerCrop()
                        .into(mImageView);
                mTitle.setText(mMovie.getOriginal_title());
                
            } else {
                Glide.with(mContext)
                        .load(UrlManager.HEADER_URL + mMovie.getPoster_path())
                        .into(mImageView);
                mTitle.setText(mMovie.getTitle());
            }
        }
    }
}
