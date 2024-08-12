package com.example.assignment3_biyushshrestha.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assignment3_biyushshrestha.DatabaseHelper.MovieDatabaseHelper;
import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import com.example.assignment3_biyushshrestha.R;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private MovieDatabaseHelper movieDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        movieDatabaseHelper = new MovieDatabaseHelper(this);
        loadFavoriteMovies();

        movieAdapter.setOnItemClickListener(movie -> {
            Intent intent = new Intent(FavoriteActivity.this, FavoriteDetailActivity.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("releaseDate", movie.getReleased());
            intent.putExtra("posterUrl", movie.getPoster());
            intent.putExtra("plot", movie.getPlot());
            intent.putExtra("genre", movie.getGenre());
            intent.putExtra("actors", movie.getActors());
            intent.putExtra("imdbRating", movie.getImdbRating());
            startActivity(intent);
        });
    }

    private void loadFavoriteMovies() {
        Cursor cursor = movieDatabaseHelper.getAllMovies();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range")
                    String title = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_TITLE));
                    @SuppressLint("Range")
                    String releaseDate = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_RELEASE_DATE));
                    @SuppressLint("Range")
                    String posterUrl = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_POSTER_URL));
                    @SuppressLint("Range")
                    String plot = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_PLOT));
                    @SuppressLint("Range")
                    String genre = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_GENRE));
                    @SuppressLint("Range")
                    String actors = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_ACTORS));
                    @SuppressLint("Range")
                    String imdbRating = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COLUMN_IMDB_RATING));

                    MovieResponse movie = new MovieResponse();
                    movie.setTitle(title);
                    movie.setReleased(releaseDate);
                    movie.setPoster(posterUrl);
                    movie.setPlot(plot);
                    movie.setGenre(genre);
                    movie.setActors(actors);
                    movie.setImdbRating(imdbRating);

                    movieAdapter.addMovie(movie);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
}
