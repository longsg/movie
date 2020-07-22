package com.example.movieapp.model.moviemodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("poster_path")
    @Expose
    private String mPoster_path;
    @SerializedName("backdrop_path")
    @Expose
    private String mBackgroud_path;
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
    @SerializedName("original_title")
    @Expose
    private String mOriginal_title;
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    @SerializedName("popularity")
    @Expose
    private String mPopularity;
    
    public Movie() {
    }
    
    @SerializedName("runtime")
    @Expose
    private int mRuntime;
    
    public Movie(String id, String poster_path, String backgroud_path, String title, float vote_average, String overview, String release_date, String original_title, String popularity, int runtime) {
        mId = id;
        mPoster_path = poster_path;
        mBackgroud_path = backgroud_path;
        mTitle = title;
        mVote_average = vote_average;
        mOverview = overview;
        mRelease_date = release_date;
        mOriginal_title = original_title;
        mPopularity = popularity;
        mRuntime = runtime;
        
    }
    
    protected Movie(Parcel in) {
        mId = in.readString();
        mPoster_path = in.readString();
        mBackgroud_path = in.readString();
        mTitle = in.readString();
        mVote_average = in.readFloat();
        mOverview = in.readString();
        mRelease_date = in.readString();
        mOriginal_title = in.readString();
        mPopularity = in.readString();
        mRuntime = in.readInt();
    }
    
    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return mId;
    }
    
    public void setId(String id) {
        mId = id;
    }
    
    public String getPoster_path() {
        return mPoster_path;
    }
    
    public void setPoster_path(String poster_path) {
        mPoster_path = poster_path;
    }
    
    public String getBackgroud_path() {
        return mBackgroud_path;
    }
    
    public void setBackgroud_path(String backgroud_path) {
        mBackgroud_path = backgroud_path;
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
    
    public String getOriginal_title() {
        return mOriginal_title;
    }
    
    public void setOriginal_title(String original_title) {
        mOriginal_title = original_title;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(mId);
        parcel.writeString(mPoster_path);
        parcel.writeString(mBackgroud_path);
        parcel.writeString(mTitle);
        parcel.writeFloat(mVote_average);
        parcel.writeString(mOverview);
        parcel.writeString(mRelease_date);
        parcel.writeString(mOriginal_title);
        parcel.writeString(mPopularity);
        parcel.writeInt(mRuntime);
    }
    
    public String getPopularity() {
        return mPopularity;
    }
    
    public void setPopularity(String popularity) {
        mPopularity = popularity;
    }
    
    public int getRuntime() {
        return mRuntime;
    }
    
    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }
}
