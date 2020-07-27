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
    @SerializedName("overview")
    @Expose
    private             String           mOverView;
    @SerializedName("name")
    @Expose
    private             String           mName;
    @SerializedName("id")
    @Expose
    private             String           mId;
    @SerializedName("profile_path")
    @Expose
    private             String           mProfile_path;
    @SerializedName("known_for")
    @Expose
    private             List<CastFilm>   mCastFilms;
    @SerializedName("gender")
    @Expose
    private             String           mGender;
    @SerializedName("biography")
    @Expose
    private             String           mBiography;
    
    public Persons() {
    }
    
    @SerializedName("birthday")
    @Expose
    private String mBirthday;
    @SerializedName("place_of_birth")
    @Expose
    private String mPlace_of_birth;
    
    protected Persons(Parcel in) {
        mOverView = in.readString();
        mName = in.readString();
        mId = in.readString();
        mProfile_path = in.readString();
        mCastFilms = in.createTypedArrayList(CastFilm.CREATOR);
        mGender = in.readString();
        mBiography = in.readString();
        mPlace_of_birth = in.readString();
        mBirthday = in.readString();
    }
    
    public Persons(String overView, String name, String id, String profile_path,
                   List<CastFilm> casts
            , String gender, String biography, String place_of_birth, String birthday) {
        mOverView = overView;
        mName = name;
        mId = id;
        mProfile_path = profile_path;
        mCastFilms = casts;
        mGender = gender;
        mBiography = biography;
        mPlace_of_birth = place_of_birth;
        mBirthday = birthday;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOverView);
        dest.writeString(mName);
        dest.writeString(mId);
        dest.writeString(mProfile_path);
        dest.writeTypedList(mCastFilms);
        dest.writeString(mGender);
        dest.writeString(mBiography);
        dest.writeString(mPlace_of_birth);
        dest.writeString(mBirthday);
    }
    
    public String getOverView() {
        return mOverView;
    }
    
    public void setOverView(String overView) {
        mOverView = overView;
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
    
    public List<CastFilm> getCastFilms() {
        return mCastFilms;
    }
    
    public void setCastFilms(List<CastFilm> casts) {
        mCastFilms = casts;
    }
    
    public String getGender() {
        return mGender;
    }
    
    public void setGender(String gender) {
        mGender = gender;
    }
    
    public String getBiography() {
        return mBiography;
    }
    
    public void setBiography(String biography) {
        mBiography = biography;
    }
    
    public String getPlace_of_birth() {
        return mPlace_of_birth;
    }
    
    public void setPlace_of_birth(String place_of_birth) {
        mPlace_of_birth = place_of_birth;
    }
    
    public String getBirthday() {
        return mBirthday;
    }
    
    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }
}
