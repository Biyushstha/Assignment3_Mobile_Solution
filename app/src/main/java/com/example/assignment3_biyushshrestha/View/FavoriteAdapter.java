package com.example.assignment3_biyushshrestha.View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.assignment3_biyushshrestha.DatabaseHelper.MovieDatabaseHelper;
import com.example.assignment3_biyushshrestha.Model.MovieResponse;
import com.example.assignment3_biyushshrestha.R;
import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MovieViewHolder> {
    private List<MovieResponse> movieList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private MovieDatabaseHelper movieDatabaseHelper;

    public FavoriteAdapter(MovieDatabaseHelper movieDatabaseHelper) {
        this.movieDatabaseHelper = movieDatabaseHelper;
    }

    // Other existing methods

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorite_adapter, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieResponse movie = movieList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.releaseDateTextView.setText(movie.getReleased());
        holder.actorsTextView.setText(movie.getActors());
        holder.ratingTextView.setText(movie.getImdbRating());
        Glide.with(holder.itemView.getContext()).load(movie.getPoster()).into(holder.posterImageView);

        holder.cardView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovies(List<MovieResponse> movies) {
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void addMovie(MovieResponse movie) {
        movieList.add(movie);
        notifyDataSetChanged();
    }

    public void removeMovie(MovieResponse movie) {
        movieList.remove(movie);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MovieResponse movie);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView, releaseDateTextView, actorsTextView, ratingTextView;
        CardView cardView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            actorsTextView = itemView.findViewById(R.id.actorsTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
