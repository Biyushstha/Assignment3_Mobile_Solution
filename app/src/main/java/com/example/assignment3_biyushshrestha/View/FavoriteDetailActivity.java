package com.example.assignment3_biyushshrestha.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.assignment3_biyushshrestha.DatabaseHelper.MovieDatabaseHelper;
import com.example.assignment3_biyushshrestha.R;

public class FavoriteDetailActivity extends AppCompatActivity {
    private MovieDatabaseHelper movieDatabaseHelper;
    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        movieDatabaseHelper = new MovieDatabaseHelper(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView posterImageView = findViewById(R.id.detailPosterImageView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView titleTextView = findViewById(R.id.detailTitleTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView releaseDateTextView = findViewById(R.id.detailReleaseDateTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView plotTextView = findViewById(R.id.detailPlotTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView genreTextView = findViewById(R.id.detailGenreTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView actorsTextView = findViewById(R.id.detailActorsTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView imdbRatingTextView = findViewById(R.id.detailImdbRatingTextView);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button deleteButton = findViewById(R.id.deleteButton);

        // Get data from intent
        movieTitle = getIntent().getStringExtra("title");
        String releaseDate = getIntent().getStringExtra("releaseDate");
        String plot = getIntent().getStringExtra("plot");
        String posterUrl = getIntent().getStringExtra("posterUrl");
        String genre = getIntent().getStringExtra("genre");
        String actors = getIntent().getStringExtra("actors");
        String imdbRating = getIntent().getStringExtra("imdbRating");

        // Set data to views
        titleTextView.setText(movieTitle);
        releaseDateTextView.setText(releaseDate);
        plotTextView.setText(plot);
        genreTextView.setText("Genre: " + genre);
        actorsTextView.setText("Actors: " + actors);
        imdbRatingTextView.setText("Rating: " + imdbRating);

        // Load poster image
        Glide.with(this).load(posterUrl).into(posterImageView);

        // Set up the delete button
        deleteButton.setOnClickListener(v -> {
            movieDatabaseHelper.deleteMovieByTitle(movieTitle);
            // Go back to FavoriteActivity
            Intent intent = new Intent(FavoriteDetailActivity.this, FavoriteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
