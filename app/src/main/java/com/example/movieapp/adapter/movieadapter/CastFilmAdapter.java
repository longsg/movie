package com.example.movieapp.adapter.movieadapter;

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
import com.example.movieapp.model.personmodel.Cast;
import com.example.movieapp.network.IClickListener;
import com.example.movieapp.network.UrlManager;

import java.util.List;

public class CastFilmAdapter extends RecyclerView.Adapter<CastFilmAdapter.CastFilmHolder> {
    private List<Cast>     mCastList;
    private Context        mContext;
    private IClickListener mIClickListener;
    
    public CastFilmAdapter(List<Cast> castList, Context context) {
        mCastList = castList;
        mContext = context;
    }
    
    public void setIClickListener(IClickListener IClickListener) {
        mIClickListener = IClickListener;
    }
    
    @NonNull
    @Override
    public CastFilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.castfilm_custom, parent, false);
        return new CastFilmHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CastFilmHolder holder, int position) {
        Cast cast = mCastList.get(position);
        holder.onBindCastFilm(cast);
    }
    
    @Override
    public int getItemCount() {
        return mCastList.size();
    }
    
    private void onBindLink(String link, ImageView imageView) {
        Glide.with(mContext)
                .load(link)
                .centerCrop()
                .into(imageView);
    }
    
    public class CastFilmHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageCast;
        private TextView  mTitle;
        private Cast      mCast;
        
        public CastFilmHolder(@NonNull View itemView) {
            super(itemView);
            mImageCast = itemView.findViewById(R.id.image_castFilm);
            mTitle = itemView.findViewById(R.id.title_castFilm);
            itemView.setOnClickListener(this);
        }
        
        public void onBindCastFilm(Cast cast) {
            mCast = cast;
            
            
            if (mCast.getPosterPath() == null)
                mImageCast.setImageResource(R.drawable.blade_poster);
            else {
                onBindLink(UrlManager.POSTER_URL + mCast.getPosterPath(), mImageCast);
            }
            
            mTitle.setText(mCast.getTitle());
        }
        
        @Override
        public void onClick(View view) {
            mIClickListener.onClick(view, getAdapterPosition());
        }
    }
}
