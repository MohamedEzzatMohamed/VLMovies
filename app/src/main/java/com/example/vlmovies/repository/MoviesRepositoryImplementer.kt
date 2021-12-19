package com.example.vlmovies.repository

import com.example.vlmovies.model.MovieDetails
import com.example.vlmovies.model.PopularMoviesResponse
import com.example.vlmovies.network.RetrofitClass
import com.example.vlmovies.utils.Constant
import retrofit2.Response

internal class MoviesRepositoryImplementer : MoviesRepository {

    override suspend fun getMoviesDetails(movieId: Int, api_key: String):
            Response<MovieDetails> {
        return RetrofitClass.apiInterface.getMovieDetails(movieId, Constant.api_key)
    }

    override suspend fun getPopularMoviesList(api_key: String): Response<PopularMoviesResponse> {
        return RetrofitClass.apiInterface.getPopularMovies(Constant.api_key)
    }

}