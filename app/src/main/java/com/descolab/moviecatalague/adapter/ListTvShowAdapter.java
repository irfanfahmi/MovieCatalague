package com.descolab.moviecatalague.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.descolab.moviecatalague.DetailTvShowActivity;
import com.descolab.moviecatalague.R;
import com.descolab.moviecatalague.model.TvShow;

import java.util.ArrayList;

public class ListTvShowAdapter extends RecyclerView.Adapter<ListTvShowAdapter.ListTvShowViewHolder> {
    private ArrayList<TvShow> tvShowArrayList;
    private Context context;
    private String tahun;

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
        holder.txtRating.setText(tvShow.getVoteAverage());
        String year = tvShow.getReleaseDate();
        tahun = year.substring(0, 4);

        holder.RbRating.setStepSize((float) 0.25);
        holder.RbRating.setMax(5);
        float a = Float.parseFloat(tvShow.getVoteAverage());
        final float d = (a * 10) / 20;

        holder.RbRating.setRating(d);
        holder.txtRating.setText(tvShow.getVoteAverage());
        holder.txtYear.setText(tahun);
        holder.txtDescription.setText(tvShow.getOverview());
        final String url = context.getString(R.string.ip_default_photo) + "w185" + tvShow.getPosterPath();
        final String urlBackdrop = context.getString(R.string.ip_default_photo) + "w500" + tvShow.getBackdropPath();

        Glide
                .with(holder.itemView.getContext())
                .load(url)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(holder.imageViewPhoto);

        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailTvShowActivity.class);
                detail.putExtra("key_title", tvShow.getTitle());
                detail.putExtra("key_rating", tvShow.getVoteAverage());
                detail.putExtra("key_realeaseDate", tvShow.getReleaseDate());
                detail.putExtra("key_overview", tvShow.getOverview());
                detail.putExtra("key_photoPath", url);
                detail.putExtra("key_photoBack", urlBackdrop);
                detail.putExtra("key_rbRating", d);
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
        private TextView txtTitle;
        private TextView txtRating;
        private AppCompatRatingBar RbRating;
        private TextView txtYear;
        private TextView txtDescription;
        private ImageView imageViewPhoto;
        private CardView cardViewItem;

        public ListTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tv_title);
            RbRating = itemView.findViewById(R.id.rb_rating);
            txtYear = itemView.findViewById(R.id.tv_year);
            txtDescription = itemView.findViewById(R.id.tv_deskripsi);
            imageViewPhoto = itemView.findViewById(R.id.iv_tvhow);
            cardViewItem = itemView.findViewById(R.id.cardViewItemTvShow);
            txtRating = itemView.findViewById(R.id.tv_rating);
        }
    }
}
