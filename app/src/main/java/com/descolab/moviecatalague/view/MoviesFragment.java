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
import com.descolab.moviecatalague.adapter.ListMoviesAdapter;
import com.descolab.moviecatalague.model.Movie;

import java.util.ArrayList;


public class MoviesFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataRating;
    private String[] dataYear;
    private String[] dataDescription;
    private String[] dataPhoto;
    private ArrayList<Movie> movieArrayList;
    private ListMoviesAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        final RecyclerView recyclerViewMovies = view.findViewById(R.id.rv_movies);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewMovies.setLayoutManager(layoutManager);
        recyclerViewMovies.setHasFixedSize(true);

        movieArrayList = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie data = new Movie();
            data.setTitle(dataTitle[i]);
            data.setRating(dataRating[i]);
            data.setYear(dataYear[i]);
            data.setDescription(dataDescription[i]);
            data.setPictmovie(dataPhoto[i]);
            Log.d("Cek", "add: "+dataPhoto[i]);

            movieArrayList.add(data);
        }
        adapter = new ListMoviesAdapter(getActivity().getApplicationContext(),movieArrayList);
        recyclerViewMovies.setAdapter(adapter);

        return view;
    }

    private void getData() {
        dataTitle = getResources().getStringArray(R.array.data_title_movies);
        dataRating = getResources().getStringArray(R.array.data_rating_movies);
        dataYear = getResources().getStringArray(R.array.data_year_movies);
        dataDescription = getResources().getStringArray(R.array.data_deskripsi_movies);
        dataPhoto = getResources().getStringArray(R.array.data_photo_movies);
    }

}
