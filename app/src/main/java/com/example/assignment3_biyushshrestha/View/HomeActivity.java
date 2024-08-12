package com.example.assignment3_biyushshrestha.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3_biyushshrestha.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);



    }

    public void onSearchButtonClick(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void onFavoriteButtonClick(View view) {
        Intent intent = new Intent(HomeActivity.this, FavoriteActivity.class);
        startActivity(intent);
    }
}