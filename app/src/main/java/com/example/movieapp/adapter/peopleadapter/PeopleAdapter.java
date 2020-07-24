package com.example.movieapp.adapter.peopleadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapp.R;
import com.example.movieapp.model.personmodel.Persons;
import com.example.movieapp.network.IClickListener;
import com.example.movieapp.network.UrlManager;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    private Context        mContext;
    private List<Persons>  mPersons;
    private IClickListener mIClickListener;
    
    public void setIClickListener(IClickListener IClickListener) {
        mIClickListener = IClickListener;
    }
    
    public PeopleAdapter(Context context, List<Persons> persons) {
        mContext = context;
        mPersons = persons;
    }
    
    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_people_layout, parent, false);
        return new PeopleViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        Persons p = mPersons.get(position);
        holder.onBindPeople(p);
    }
    
    @Override
    public int getItemCount() {
        return mPersons.size();
    }
    
    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Persons mPersons;
        private ImageView mImageView;
        private TextView mName;
        
        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_people);
            mImageView = itemView.findViewById(R.id.image_people);
            itemView.setOnClickListener(this);
        }
        
        public void onBindPeople(Persons p) {
            mPersons = p;
            if (!mPersons.getName().isEmpty() && !mPersons.getProfile_path().isEmpty()) {
                Glide.with(mContext)
                        .load(UrlManager.POSTER_URL + mPersons.getProfile_path())
                        .apply(RequestOptions.circleCropTransform())
                        .into(mImageView);
                
                mName.setText(mPersons.getName());
            }
        }
        
        @Override
        public void onClick(View view) {
            mIClickListener.onClick(view, getAdapterPosition());
        }
    }
    
}
