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

public class DetailTvShowActivity extends AppCompatActivity {
    private String title ;
    private String rating ;
    private String releasedate ;
    private String overview ;
    private String photoPath ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);
        TextView txtTitle = findViewById(R.id.text_title);
        TextView txtRating = findViewById(R.id.text_rating);
        TextView txtYear = findViewById(R.id.text_year);
        TextView txtDescription = findViewById(R.id.text_description);
        ImageView imgMovies = findViewById(R.id.image_movie);


        title = getIntent().getStringExtra("key_title");
        rating = getIntent().getStringExtra("key_rating");
        releasedate = getIntent().getStringExtra("key_releasedate");
        overview = getIntent().getStringExtra("key_overview");
        photoPath = getIntent().getStringExtra("key_photoPath");
        txtTitle.setText(title);
        txtRating.setText(rating);
        txtYear.setText(releasedate);
        txtDescription.setText(overview);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            TextView textView = toolbar.findViewById(R.id.textView_toolbar_detail);
            textView.setText(title);
        }

        Log.d("DetailActivity", "onCreate: Cek isi Title " + title);
        Glide
                .with(this)
                .load(photoPath)
                .centerCrop()
                .into(imgMovies);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
