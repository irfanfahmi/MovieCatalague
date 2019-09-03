package com.descolab.moviecatalague;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.descolab.moviecatalague.model.TvShow;

public class DetailTvShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);
        TextView txtTitle = findViewById(R.id.text_title);
        TextView txtRating = findViewById(R.id.text_rating);
        TextView txtGenre = findViewById(R.id.text_genre);
        TextView txtYear =  findViewById(R.id.text_year);
        TextView txtDescription =  findViewById(R.id.text_description);
        ImageView imgMovies =  findViewById(R.id.image_movie);
        ImageView imgMovies2 =  findViewById(R.id.image_bg);

        TvShow tvshow = getIntent().getParcelableExtra("key_tvshow");
        txtTitle.setText(tvshow.getTitle());
        txtRating.setText(tvshow.getRating());
        txtGenre.setText(tvshow.getGenre());
        txtYear.setText(tvshow.getYear());
        txtDescription.setText(tvshow.getDescription());

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        TextView textView = toolbar.findViewById(R.id.textView_toolbar_detail);
        textView.setText(tvshow.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Log.d("DetailActivity", "onCreate: Cek isi Title "+tvshow.getTitle());
        Glide
                .with(this)
                .load(tvshow.getPicttv())
                .centerCrop()
                .into(imgMovies);
        Glide
                .with(this)
                .load(tvshow.getPicttv())
                .centerCrop()
                .into(imgMovies2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
