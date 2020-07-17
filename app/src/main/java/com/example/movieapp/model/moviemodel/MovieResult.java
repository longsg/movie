package com.example.movieapp.model.moviemodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult implements Parcelable {
    
    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }
        
        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };
    @SerializedName("results")
    @Expose
    private List<Movie> resultsMovies;
    @SerializedName("page")
    @Expose
    private int page;
    @Expose
    @SerializedName("total_results")
    private int total_result;
    @SerializedName("total_pages")
    @Expose
    private int total_page;
    
    public MovieResult() {
    }
    
    public MovieResult(List<Movie> resultsMovies, int page,
                       int total_result, int total_page) {
        this.resultsMovies = resultsMovies;
        this.page = page;
        this.total_result = total_result;
        this.total_page = total_page;
    }
    
    protected MovieResult(Parcel in) {
        page = in.readInt();
        total_result = in.readInt();
        total_page = in.readInt();
    }
    
    public List<Movie> getResultsMovies() {
        return resultsMovies;
    }
    
    public void setResultsMovies(List<Movie> resultsMovies) {
        this.resultsMovies = resultsMovies;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getTotal_result() {
        return total_result;
    }
    
    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }
    
    public int getTotal_page() {
        return total_page;
    }
    
    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeInt(page);
        parcel.writeInt(total_result);
        parcel.writeInt(total_page);
    }
}
