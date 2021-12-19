package com.example.vlmovies.repository

import com.example.vlmovies.model.MovieDetails
import com.example.vlmovies.model.PopularMoviesResponse
import retrofit2.Response

internal interface MoviesRepository {

    suspend fun getMoviesDetails(
        movieId: Int,
        api_key: String
    ): Response<MovieDetails>? = null

    suspend fun getPopularMoviesList(
        api_key: String
    ): Response<PopularMoviesResponse>? = null

}