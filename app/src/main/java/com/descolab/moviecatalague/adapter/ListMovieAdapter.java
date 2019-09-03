package com.descolab.moviecatalague.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.descolab.moviecatalague.R;
import com.descolab.moviecatalague.model.Movie;

import java.util.ArrayList;

public class ListMovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public void setListMovieAdapter(ArrayList<Movie> movie) {
        this.movies = movie;
    }
    public ListMovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_movie_adapter, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie hero = (Movie) getItem(position);
        viewHolder.bind(hero);
        return convertView;
    }

    private class ViewHolder {
        private TextView txtTitle;
        private TextView txtRating;
        private TextView txtYear;
        private TextView txtDescription;
        private ImageView imageViewPhoto;
        ViewHolder(View view) {
            txtTitle = view.findViewById(R.id.tv_title);
            txtRating = view.findViewById(R.id.tv_rating);
            txtYear = view.findViewById(R.id.tv_year);
            txtDescription = view.findViewById(R.id.tv_deskripsi);
            imageViewPhoto = view.findViewById(R.id.iv_movie);
        }
        void bind(Movie movie) {
            Log.d("Adapter", "bind: pict"+movie.getPictmovie());
            txtTitle.setText(movie.getTitle());
            txtRating.setText(movie.getRating());
            txtYear.setText("("+movie.getYear()+")");
            txtDescription.setText(movie.getDescription());
            //imageViewPhoto.setImageResource(movie.getPictmovie());
            Glide
                    .with(context)
                    .load(movie.getPictmovie())
                    .centerCrop()
                    .into(imageViewPhoto);
        }
    }
}
