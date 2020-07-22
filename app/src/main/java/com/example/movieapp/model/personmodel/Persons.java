package com.example.movieapp.model.personmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Persons implements Parcelable {
    public static final Creator<Persons> CREATOR = new Creator<Persons>() {
        @Override
        public Persons createFromParcel(Parcel in) {
            return new Persons(in);
        }
        
        @Override
        public Persons[] newArray(int size) {
            return new Persons[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("profile_path")
    @Expose
    private String mProfile_path;
    @SerializedName("known_for")
    @Expose
    private List<CastFilm> mCastFilmList;
    
    public Persons() {
    }
    
    public Persons(String name, String id, String profile_path, List<CastFilm> castFilmList) {
        mName = name;
        mId = id;
        mProfile_path = profile_path;
        mCastFilmList = castFilmList;
    }
    
    protected Persons(Parcel in) {
        mName = in.readString();
        mId = in.readString();
        mProfile_path = in.readString();
    }
    
    public String getName() {
        return mName;
    }
    
    public void setName(String name) {
        mName = name;
    }
    
    public String getId() {
        return mId;
    }
    
    public void setId(String id) {
        mId = id;
    }
    
    public String getProfile_path() {
        return mProfile_path;
    }
    
    public void setProfile_path(String profile_path) {
        mProfile_path = profile_path;
    }
    
    public List<CastFilm> getCastFilmList() {
        return mCastFilmList;
    }
    
    public void setCastFilmList(List<CastFilm> castFilmList) {
        mCastFilmList = castFilmList;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(mName);
        parcel.writeString(mId);
        parcel.writeString(mProfile_path);
    }
}
