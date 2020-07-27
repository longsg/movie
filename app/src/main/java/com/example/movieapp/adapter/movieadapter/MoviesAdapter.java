package com.example.movieapp.adapter.movieadapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.moviemodel.Movie;
import com.example.movieapp.network.IClickListener;
import com.example.movieapp.network.UrlManager;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private IClickListener mIClickListener;
    private Context        mContext;
    private List<Movie>    mMovieList;
    
    public MoviesAdapter(Context context, List<Movie> movieList) {
        mContext = context;
        mMovieList = movieList;
    }
    
    public void setIClickListener(IClickListener IClickListener) {
        mIClickListener = IClickListener;
    }
    
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_popular_movie, parent, false);
        return new MovieViewHolder(view);
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.onBindMoview(movie);
    }
    
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
    
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView  mTitle;
        private Movie     currentMovie;
        
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_movie);
            mTitle = itemView.findViewById(R.id.title_movie);
            
            itemView.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View view) {
            mIClickListener.onClick(view, getAdapterPosition());
        }
        
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onBindMoview(Movie movie) {
            currentMovie = movie;
            if (currentMovie.getTitle() == null && currentMovie.getPoster_path() == null) {
                mTitle.setText(currentMovie.getOriginal_title());
                mImageView.setImageDrawable(mContext.getDrawable(R.drawable.blade_poster));
            } else {
                Glide.with(mContext)
                        .load(UrlManager.POSTER_URL + currentMovie.getPoster_path())
                        .into(mImageView);
                mTitle.setText(currentMovie.getTitle());
            }
        }
    }
}
