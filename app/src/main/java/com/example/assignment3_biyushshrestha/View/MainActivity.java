package com.example.assignment3_biyushshrestha.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assignment3_biyushshrestha.R;
import com.example.assignment3_biyushshrestha.ViewModel.MovieViewModel;
import com.example.assignment3_biyushshrestha.ViewModel.MovieViewModelFactory;
import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;
    private TextInputEditText searchBar;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(this, new MovieViewModelFactory()).get(MovieViewModel.class);
        searchBar = findViewById(R.id.searchBar);
        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        MaterialButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            String movieTitle = searchBar.getText().toString();
            movieViewModel.getMovieData(movieTitle).observe(this, movieResponse -> {
                if (movieResponse != null && movieResponse.getTitle() != null) {
                    movieAdapter.setMovieResponse(movieResponse);
                } else {
                    movieAdapter.clearMovies();
                    showNoDataFoundMessage();
                }
            });
        });

        MaterialButton clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> onClearButtonClick());

        movieAdapter.setOnItemClickListener(movie -> {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("releaseDate", movie.getReleased());
            intent.putExtra("plot", movie.getPlot());
            intent.putExtra("posterUrl", movie.getPoster());
            intent.putExtra("genre", movie.getGenre());
            intent.putExtra("actors", movie.getActors());
            intent.putExtra("imdbRating", movie.getImdbRating());
            startActivity(intent);
        });
    }
    private void showNoDataFoundMessage() {
        Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
    }
    public void onClearButtonClick() {
        searchBar.setText("");
        movieAdapter.clearMovies();
        movieAdapter.notifyDataSetChanged();
    }
}
