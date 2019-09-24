package com.descolab.moviecatalague;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailTvShowActivity extends AppCompatActivity {
    private String title;
    private String rating;
    private String releasedate;
    private String overview;
    private String photoPath;
    private String photoBack;
    private String valueRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);
        TextView txtTitle = findViewById(R.id.text_title);
        TextView txtRating = findViewById(R.id.text_rating);
        TextView txtYear = findViewById(R.id.text_year);
        RatingBar rbRating = findViewById(R.id.rb_rating);
        TextView txtDescription = findViewById(R.id.text_description);
        ImageView imgTv = findViewById(R.id.image_tv);
        ImageView imgTvback = findViewById(R.id.image_background);


        title = getIntent().getStringExtra("key_title");
        rating = getIntent().getStringExtra("key_rating");
        releasedate = getIntent().getStringExtra("key_realeaseDate");
        overview = getIntent().getStringExtra("key_overview");
        photoPath = getIntent().getStringExtra("key_photoPath");
        photoBack = getIntent().getStringExtra("key_photoBack");
        valueRating = getIntent().getStringExtra("key_rbRating");

        txtTitle.setText(title);
        txtRating.setText(rating);
        txtYear.setText(getApplicationContext().getString(R.string.txt_release_date) + " : " + releasedate);
        txtDescription.setText(overview);
        float a = Float.parseFloat(rating);
        final float d = (a * 10) / 20;
        rbRating.setRating(d);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
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
                .placeholder(R.drawable.no_image)
                .into(imgTv);

        Glide
                .with(this)
                .load(photoBack)
                .centerCrop()
                .placeholder(R.drawable.no_image)
                .into(imgTvback);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
