package com.example.vlmovies.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    val page: Int,

    @SerializedName("results")
    var moviesList: ArrayList<Movie>? = ArrayList(),

    @SerializedName("total_pages")
    val pages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)