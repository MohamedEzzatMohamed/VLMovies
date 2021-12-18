package com.example.vlmovies.network

import com.example.vlmovies.model.MovieDetails
import com.example.vlmovies.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit interface to implement the api functions
 */
interface ApiInterface {

    /**
     * get function get list of movies
     * params[api_key]
     * response: list of movies
     */
    @GET("movie/popular/")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String
    ): Response<PopularMoviesResponse>

    /**
     * post function to verify the user by creating a new user
     * params[UserDevice] with the data and the code
     * the response contain the user data and the token that will use to post the location
     * the response is a Device if success
     */
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetails>
}