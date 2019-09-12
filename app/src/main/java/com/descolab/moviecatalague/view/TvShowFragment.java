package com.descolab.moviecatalague.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.descolab.moviecatalague.R;
import com.descolab.moviecatalague.adapter.ListTvShowAdapter;
import com.descolab.moviecatalague.model.TvShow;

import java.util.ArrayList;


public class TvShowFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataGenre;
    private String[] dataRating;
    private String[] dataYear;
    private String[] dataDescription;
    private String[] dataPhoto;
    private ArrayList<TvShow> tvShowArrayList;
    private ListTvShowAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        final RecyclerView recyclerViewTvShow = view.findViewById(R.id.rv_tvmovies);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewTvShow.setLayoutManager(layoutManager);
        recyclerViewTvShow.setHasFixedSize(true);

        tvShowArrayList = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            TvShow data = new TvShow();
            data.setTitle(dataTitle[i]);
            data.setRating(dataRating[i]);
            data.setGenre(dataGenre[i]);
            data.setYear(dataYear[i]);
            data.setDescription(dataDescription[i]);
            data.setPicttv(dataPhoto[i]);
            Log.d("Cek", "add: "+dataPhoto[i]);

            tvShowArrayList.add(data);
        }
        adapter = new ListTvShowAdapter(getActivity().getApplicationContext(),tvShowArrayList);
        recyclerViewTvShow.setAdapter(adapter);



        return view;
    }

    private void getData() {
        dataTitle = getResources().getStringArray(R.array.data_title_tvshow);
        dataRating = getResources().getStringArray(R.array.data_rating_tvshow);
        dataGenre = getResources().getStringArray(R.array.data_genre_tvshow);
        dataYear = getResources().getStringArray(R.array.data_year_tvshow);
        dataDescription = getResources().getStringArray(R.array.data_deskripsi_tvshow);
        dataPhoto = getResources().getStringArray(R.array.data_photo_tvshow);
    }


}
