package com.example.movieapp.model.personmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastFilm implements Parcelable {
    public static final Creator<CastFilm> CREATOR = new Creator<CastFilm>() {
        @Override
        public CastFilm createFromParcel(Parcel in) {
            return new CastFilm(in);
        }
    
        @Override
        public CastFilm[] newArray(int size) {
            return new CastFilm[size];
        }
    };
    
    public CastFilm() {
    }
    
    @SerializedName("cast")
    private List<Cast> mCasts;
    
    public CastFilm(List<Cast> casts) {
        mCasts = casts;
    }
    
    protected CastFilm(Parcel in) {
    }
    
    public List<Cast> getCasts() {
        return mCasts;
    }
    
    public void setCasts(List<Cast> casts) {
        mCasts = casts;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    
    }
}
