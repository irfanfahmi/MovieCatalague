package com.descolab.moviecatalague.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.descolab.moviecatalague.DetailMoviesActivity;
import com.descolab.moviecatalague.R;
import com.descolab.moviecatalague.model.Movie;

import java.util.ArrayList;

public class ListMoviesAdapter extends RecyclerView.Adapter<ListMoviesAdapter.ListMoviesViewHolder> {
    private ArrayList<Movie> movieArrayList;
    private Context context;
    private String tahun;

    public ListMoviesAdapter(Context context,ArrayList<Movie> list) {
        this.context = context;
        this.movieArrayList = list;
    }

    @NonNull
    @Override
    public ListMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movie_adapter, viewGroup, false);

        ListMoviesViewHolder viewHolder = new ListMoviesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListMoviesViewHolder holder, final int i) {
        final Movie movie = movieArrayList.get(i);
        holder.txtTitle.setText(movie.getTitle());
        holder.RbRating.setStepSize((float) 0.25);
        holder.RbRating.setMax(5);
        float a =  Float.parseFloat(movie.getVoteAverage()) ;
        final float d=  (a*10) /20 ;
        holder.RbRating.setRating(d);
        holder.txtRating.setText(movie.getVoteAverage());
        holder.txtYear.setText(tahun);
        holder.txtDescription.setText(movie.getOverview());

        String year = movie.getReleaseDate();
        tahun = year.substring(0,4);

        final String url = context.getString(R.string.ip_default_photo)+"w185"+movie.getPosterPath();
        final String urlBackdrop = context.getString(R.string.ip_default_photo)+"w500"+movie.getBackdropPath();
        Glide
                .with(holder.itemView.getContext())
                .load(url)
                .centerCrop()
                .into(holder.imageViewPhoto);

        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailMoviesActivity.class);
                detail.putExtra("key_title", movie.getTitle());
                detail.putExtra("key_rating", movie.getVoteAverage());
                detail.putExtra("key_realeaseDate", movie.getReleaseDate());
                detail.putExtra("key_overview", movie.getOverview());
                detail.putExtra("key_photoPath", url);
                detail.putExtra("key_photoBack",urlBackdrop);
                detail.putExtra("key_rbRating", d);

                detail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (movieArrayList != null) {
            return movieArrayList.size();
        }
        return 0;
    }

    public class ListMoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private AppCompatRatingBar RbRating;
        private TextView txtRating;
        private TextView txtYear;
        private TextView txtDescription;
        private ImageView imageViewPhoto;
        private CardView cardViewItem;

        public ListMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tv_title);
            RbRating = itemView.findViewById(R.id.rb_rating);
            txtYear = itemView.findViewById(R.id.tv_year);
            txtDescription = itemView.findViewById(R.id.tv_deskripsi);
            imageViewPhoto = itemView.findViewById(R.id.iv_movie);
            cardViewItem = itemView.findViewById(R.id.cardViewItemMovies);
            txtRating = itemView.findViewById(R.id.tv_rating);


        }
    }
}
