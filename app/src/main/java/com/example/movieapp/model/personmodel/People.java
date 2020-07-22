package com.example.movieapp.model.personmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class People implements Parcelable {
    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }
        
        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("total_results")
    @Expose
    private int mTotal_result;
    @SerializedName("total_pages")
    @Expose
    private int mTotal_pages;
    @SerializedName("results")
    @Expose
    private List<Persons> mPersonsList;
    
    public People() {
    }
    
    public People(String id, int total_result, int total_pages, List<Persons> personsList) {
        this.id = id;
        mTotal_result = total_result;
        mTotal_pages = total_pages;
        mPersonsList = personsList;
    }
    
    protected People(Parcel in) {
        id = in.readString();
        mTotal_result = in.readInt();
        mTotal_pages = in.readInt();
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getTotal_result() {
        return mTotal_result;
    }
    
    public void setTotal_result(int total_result) {
        mTotal_result = total_result;
    }
    
    public int getTotal_pages() {
        return mTotal_pages;
    }
    
    public void setTotal_pages(int total_pages) {
        mTotal_pages = total_pages;
    }
    
    public List<Persons> getPersonsList() {
        return mPersonsList;
    }
    
    public void setPersonsList(List<Persons> personsList) {
        mPersonsList = personsList;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(id);
        parcel.writeInt(mTotal_result);
        parcel.writeInt(mTotal_pages);
    }
}
