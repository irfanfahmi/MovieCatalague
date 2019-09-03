package com.descolab.moviecatalague.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.descolab.moviecatalague.DetailTvShow;
import com.descolab.moviecatalague.R;
import com.descolab.moviecatalague.model.TvShow;

import java.util.ArrayList;

public class ListTvShowAdapter extends RecyclerView.Adapter<ListTvShowAdapter.ListTvShowViewHolder> {
    ArrayList<TvShow> tvShowArrayList;

    Context context;

    public void setListTvShowAdapter(ArrayList<TvShow> tvShow) {
        this.tvShowArrayList = tvShow;
    }

    public ListTvShowAdapter(Context context, ArrayList<TvShow> list) {
        this.context = context;
        this.tvShowArrayList = list;
    }

    @NonNull
    @Override
    public ListTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.item_list_tvshow_adapter, viewGroup, false);

        ListTvShowViewHolder viewHolder = new ListTvShowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListTvShowViewHolder holder, final int i) {
        final TvShow tvShow = tvShowArrayList.get(i);
        holder.txtTitle.setText(tvShow.getTitle());
        holder.txtRating.setText(tvShow.getRating());
        holder.txtYear.setText(tvShow.getYear());
        holder.txtGenre.setText(tvShow.getGenre());
        holder.txtDescription.setText(tvShow.getDescription());

        Glide
                .with(holder.itemView.getContext())
                .load(tvShow.getPicttv())
                .centerCrop()
                .into(holder.imageViewPhoto);

        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailTvShow.class);
                detail.putExtra("key_tvshow", tvShowArrayList.get(i));
                detail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (tvShowArrayList != null) {
            return tvShowArrayList.size();
        }
        return 0;
    }

    public class ListTvShowViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtRating;
        TextView txtGenre;
        TextView txtYear;
        TextView txtDescription;
        ImageView imageViewPhoto;
        CardView cardViewItem;

        public ListTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtRating = itemView.findViewById(R.id.tv_rating);
            txtYear = itemView.findViewById(R.id.tv_year);
            txtGenre = itemView.findViewById(R.id.tv_genre);
            txtDescription = itemView.findViewById(R.id.tv_deskripsi);
            imageViewPhoto = itemView.findViewById(R.id.iv_movie);
            cardViewItem = itemView.findViewById(R.id.cardViewItemTvShow);
        }
    }
}
