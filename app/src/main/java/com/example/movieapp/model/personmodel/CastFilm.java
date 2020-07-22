package com.example.movieapp.model.personmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("poster_path")
    @Expose
    private String mPoster_path;
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("backdrop_path")
    @Expose
    private String mBackdrop_path;
    @SerializedName("original_title")
    @Expose
    private String mOriginal_title;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("vote_average")
    @Expose
    private float mVote_average;
    @SerializedName("overview")
    @Expose
    private String mOverview;
    @SerializedName("release_date")
    @Expose
    private String mRelease_date;
    
    public CastFilm() {
    }
    
    public CastFilm(String poster_path, String id, String backdrop_path, String original_title, String title, float vote_average, String overview, String release_date) {
        mPoster_path = poster_path;
        mId = id;
        mBackdrop_path = backdrop_path;
        mOriginal_title = original_title;
        mTitle = title;
        mVote_average = vote_average;
        mOverview = overview;
        mRelease_date = release_date;
    }
    
    protected CastFilm(Parcel in) {
        mPoster_path = in.readString();
        mId = in.readString();
        mBackdrop_path = in.readString();
        mOriginal_title = in.readString();
        mTitle = in.readString();
        mVote_average = in.readFloat();
        mOverview = in.readString();
        mRelease_date = in.readString();
    }
    
    public String getPoster_path() {
        return mPoster_path;
    }
    
    public void setPoster_path(String poster_path) {
        mPoster_path = poster_path;
    }
    
    public String getId() {
        return mId;
    }
    
    public void setId(String id) {
        mId = id;
    }
    
    public String getBackdrop_path() {
        return mBackdrop_path;
    }
    
    public void setBackdrop_path(String backdrop_path) {
        mBackdrop_path = backdrop_path;
    }
    
    public String getOriginal_title() {
        return mOriginal_title;
    }
    
    public void setOriginal_title(String original_title) {
        mOriginal_title = original_title;
    }
    
    public String getTitle() {
        return mTitle;
    }
    
    public void setTitle(String title) {
        mTitle = title;
    }
    
    public float getVote_average() {
        return mVote_average;
    }
    
    public void setVote_average(float vote_average) {
        mVote_average = vote_average;
    }
    
    public String getOverview() {
        return mOverview;
    }
    
    public void setOverview(String overview) {
        mOverview = overview;
    }
    
    public String getRelease_date() {
        return mRelease_date;
    }
    
    public void setRelease_date(String release_date) {
        mRelease_date = release_date;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(mPoster_path);
        parcel.writeString(mId);
        parcel.writeString(mBackdrop_path);
        parcel.writeString(mOriginal_title);
        parcel.writeString(mTitle);
        parcel.writeFloat(mVote_average);
        parcel.writeString(mOverview);
        parcel.writeString(mRelease_date);
    }
}
