package com.descolab.moviecatalague.model;

public interface TvShowSourcesCallBack {
    void onSuccess(TvShowResponse tvShowResponse);

    void onFailed(String error);
}
