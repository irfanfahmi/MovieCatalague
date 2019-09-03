package com.descolab.moviecatalague.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int id;
    private String title;
    private String year;
    private String rating;
    private String genre;
    private String description;
    private String picttv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPicttv() {
        return picttv;
    }

    public void setPicttv(String picttv) {
        this.picttv = picttv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.rating);
        dest.writeString(this.genre);
        dest.writeString(this.description);
        dest.writeString(this.picttv);
    }

    public TvShow() {
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.year = in.readString();
        this.rating = in.readString();
        this.genre = in.readString();
        this.description = in.readString();
        this.picttv = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
