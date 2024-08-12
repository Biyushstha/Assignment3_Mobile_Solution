package com.example.assignment3_biyushshrestha.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignment3_biyushshrestha.Model.MovieResponse;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FAVORITES = "favorites";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_POSTER_URL = "poster_url";
    public static final String COLUMN_PLOT = "plot";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_ACTORS = "actors";
    public static final String COLUMN_IMDB_RATING = "imdb_rating";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_FAVORITES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_RELEASE_DATE + " TEXT, " +
                    COLUMN_POSTER_URL + " TEXT, " +
                    COLUMN_PLOT + " TEXT, " +
                    COLUMN_GENRE + " TEXT, " +
                    COLUMN_ACTORS + " TEXT, " +
                    COLUMN_IMDB_RATING + " TEXT);";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.d("MovieDatabaseHelper", "Database and table created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addMovie(MovieResponse movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_RELEASE_DATE, movie.getReleased());
        values.put(COLUMN_POSTER_URL, movie.getPoster());
        values.put(COLUMN_PLOT, movie.getPlot());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_ACTORS, movie.getActors());
        values.put(COLUMN_IMDB_RATING, movie.getImdbRating());

        db.insert(TABLE_FAVORITES, null, values);
        db.close();

        // Log message to confirm data insertion
        Log.d("MovieDatabaseHelper", "Movie added: " + movie.getTitle());
    }

    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_FAVORITES, null, null, null, null, null, null);
    }
    public boolean isMovieInFavorites(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT 1 FROM " + TABLE_FAVORITES + " WHERE " + COLUMN_TITLE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{title});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void deleteMovieByTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, COLUMN_TITLE + " = ?", new String[]{title});
        db.close();


        Log.d("MovieDatabaseHelper", "Movie deleted: " + title);
    }
}
