package com.example.assignment3_biyushshrestha.ViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieViewModelFactory implements ViewModelProvider.Factory {
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}