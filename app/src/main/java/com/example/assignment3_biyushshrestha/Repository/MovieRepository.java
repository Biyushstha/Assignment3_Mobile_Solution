package com.example.assignment3_biyushshrestha.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment3_biyushshrestha.BuildConfig;
import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import com.example.assignment3_biyushshrestha.Network.MovieService;
import com.example.assignment3_biyushshrestha.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private MovieService movieService;
    private final String apiKey = BuildConfig.OMDB_API_KEY; // Fetch the API key from BuildConfig

    public MovieRepository() {
        movieService = RetrofitClient.getRetrofitInstance().create(MovieService.class);
    }

    public LiveData<MovieResponse> getMovie(String title) {
        final MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();

        Call<MovieResponse> call = movieService.getMovie(title, apiKey);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieData.setValue(response.body());
                    // Log the response for debugging
                    Log.d("MovieRepository", "Movie Response: " + response.body().toString());
                } else {
                    Log.e("MovieRepository", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("MovieRepository", "API call failed: " + t.getMessage());
            }
        });

        return movieData;
    }

    public LiveData<List<MovieResponse>> getFavoritesMovies() {
        // Replace this with your actual implementation to fetch favorite movies from the database
        MutableLiveData<List<MovieResponse>> liveData = new MutableLiveData<>();
        // Fetch and set data
        return liveData;
    }
}
