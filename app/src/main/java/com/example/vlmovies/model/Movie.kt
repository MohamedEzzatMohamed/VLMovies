package com.example.vlmovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genre_ids")
    val genreIds: ArrayList<Int>? = ArrayList(),

    val id: Int,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,

    val popularity: Double,

    @SerializedName("poster_path")
    val poster: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    val video: Boolean,

    @SerializedName("voteAverage")
    val voteAverage: Double,

    @SerializedName("voteCount")
    val voteCount: Int

)