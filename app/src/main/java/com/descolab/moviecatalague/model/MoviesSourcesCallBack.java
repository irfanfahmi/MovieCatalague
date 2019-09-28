package com.descolab.moviecatalague.model;

public interface MoviesSourcesCallBack {
    void onSuccess(MoviesResponse moviesResponse);

    void onFailed(String error);
}
