package com.descolab.moviecatalague.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesResponse {

    @SerializedName("results")
    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults(){
        return results;
    }
}