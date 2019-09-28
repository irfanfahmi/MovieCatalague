package com.descolab.moviecatalague.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowResponse {

    @SerializedName("results")
    private ArrayList<TvShow> results;

    public ArrayList<TvShow> getResults(){
        return results;
    }
}