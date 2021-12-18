package com.example.vlmovies.ui.movieslist

import com.example.vlmovies.model.Movie

interface MoviesListNavigator {
    fun showMovieDetails(movie: Movie)
}