package com.example.assignment3_biyushshrestha.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.assignment3_biyushshrestha.DatabaseHelper.MovieDatabaseHelper;
import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import com.example.assignment3_biyushshrestha.R;

public class MovieDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView posterImageView = findViewById(R.id.posterImageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView releaseDateTextView = findViewById(R.id.releaseDateTextView);
        TextView plotTextView = findViewById(R.id.plotTextView);
        TextView genreTextView = findViewById(R.id.genreTextView);
        TextView actorsTextView = findViewById(R.id.actorsTextView);
        TextView imdbRatingTextView = findViewById(R.id.imdbRatingTextView);
        Button favoriteButton = findViewById(R.id.favoriteButton);

        // Get data from intent
        String title = getIntent().getStringExtra("title");
        String releaseDate = getIntent().getStringExtra("releaseDate");
        String plot = getIntent().getStringExtra("plot");
        String posterUrl = getIntent().getStringExtra("posterUrl");
        String genre = getIntent().getStringExtra("genre");
        String actors = getIntent().getStringExtra("actors");
        String imdbRating = getIntent().getStringExtra("imdbRating");

        // Set data to views
        titleTextView.setText(title);
        releaseDateTextView.setText(releaseDate);
        plotTextView.setText(plot);
        genreTextView.setText("Genre: " + genre);
        actorsTextView.setText("Actors: " + actors);
        imdbRatingTextView.setText("Rating: " + imdbRating);

        // Load poster image
        Glide.with(this).load(posterUrl).into(posterImageView);

        // Create MovieResponse object
        MovieResponse movie = new MovieResponse();
        movie.setTitle(title);
        movie.setReleased(releaseDate);
        movie.setPoster(posterUrl);
        movie.setPlot(plot);
        movie.setGenre(genre);
        movie.setActors(actors);
        movie.setImdbRating(imdbRating);

        // Create MovieDatabaseHelper instance
        MovieDatabaseHelper dbHelper = new MovieDatabaseHelper(this);

        favoriteButton.setOnClickListener(v -> {
            if (dbHelper.isMovieInFavorites(title)) {
                Toast.makeText(this, "Movie is already in Favorites", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addMovie(movie);
                Toast.makeText(this, "Movie Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
