package com.example.movieapp.model.personmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
// %s /string need replace/ new string

public class Cast implements Parcelable {
    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }
        
        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };
    @SerializedName("character")
    private             String        character;
    @SerializedName("credit_id")
    private             String        creditId;
    @SerializedName("release_date")
    private             String        releaseDate;
    @SerializedName("vote_count")
    private             Integer       voteCount;
    @SerializedName("video")
    private             Boolean       video;
    @SerializedName("adult")
    private             Boolean       adult;
    @SerializedName("vote_average")
    private             float         voteAverage;
    @SerializedName("title")
    private             String        title;
    @SerializedName("popularity")
    private             Double        popularity;
    @SerializedName("id")
    private             Integer       id;
    @SerializedName("backdrop_path")
    private             Object        backdropPath;
    @SerializedName("overview")
    private             String        overview;
    @SerializedName("poster_path")
    private             String        posterPath;
    
    protected Cast(Parcel in) {
        character = in.readString();
        creditId = in.readString();
        releaseDate = in.readString();
        if (in.readByte() == 0) { voteCount = null; } else { voteCount = in.readInt(); }
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null : tmpVideo == 1;
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        voteAverage = in.readFloat();
        title = in.readString();
        if (in.readByte() == 0) { popularity = null; } else { popularity = in.readDouble(); }
        if (in.readByte() == 0) { id = null; } else { id = in.readInt(); }
        overview = in.readString();
        posterPath = in.readString();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        
        parcel.writeString(character);
        parcel.writeString(creditId);
        parcel.writeString(releaseDate);
        if (voteCount == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(voteCount);
        }
        parcel.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        parcel.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        parcel.writeFloat(voteAverage);
        parcel.writeString(title);
        if (popularity == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(popularity);
        }
        if (id == null) { parcel.writeByte((byte) 0); } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(overview);
        parcel.writeString(posterPath);
    }
    
    public String getCharacter() {
        return character;
    }
    
    public void setCharacter(String character) {
        this.character = character;
    }
    
    public String getCreditId() {
        return creditId;
    }
    
    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }
    
    public String getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public Integer getVoteCount() {
        return voteCount;
    }
    
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
    
    public Boolean getVideo() {
        return video;
    }
    
    public void setVideo(Boolean video) {
        this.video = video;
    }
    
    public Boolean getAdult() {
        return adult;
    }
    
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
    
    public float getVoteAverage() {
        return voteAverage;
    }
    
    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Double getPopularity() {
        return popularity;
    }
    
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Object getBackdropPath() {
        return backdropPath;
    }
    
    public void setBackdropPath(Object backdropPath) {
        this.backdropPath = backdropPath;
    }
    
    public String getOverview() {
        return overview;
    }
    
    public void setOverview(String overview) {
        this.overview = overview;
    }
    
    public String getPosterPath() {
        return posterPath;
    }
    
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
