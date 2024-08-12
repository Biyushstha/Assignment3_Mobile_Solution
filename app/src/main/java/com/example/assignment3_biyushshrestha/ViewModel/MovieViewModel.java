package com.example.assignment3_biyushshrestha.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import com.example.assignment3_biyushshrestha.Repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MovieViewModel() {
        movieRepository = new MovieRepository();
    }

    public LiveData<MovieResponse> getMovieData(String title) {
        return movieRepository.getMovie(title);
    }


}