package com.example.assignment3_biyushshrestha.Network;

import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("/")
    Call<MovieResponse> getMovie(@Query("t") String title, @Query("apikey") String apiKey);
}