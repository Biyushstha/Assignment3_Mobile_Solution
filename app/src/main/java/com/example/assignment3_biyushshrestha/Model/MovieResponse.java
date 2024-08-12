package com.example.assignment3_biyushshrestha.Model;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("Released")
    private String released;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("imdbRating")
    private String imdbRating;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getReleased() { return released; }
    public void setReleased(String released) { this.released = released; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getPlot() { return plot; }
    public void setPlot(String plot) { this.plot = plot; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }
    public String getImdbRating() { return imdbRating; }
    public void setImdbRating(String imdbRating) { this.imdbRating = imdbRating; }

}
