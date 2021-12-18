package com.example.vlmovies.model

data class MovieDetails(
    var adult: Boolean? = false,
    var backdrop_path: String? = "",
    var belongs_to_collection: BelongsToCollection? = null,
    var budget: Int? = 0,
    var genres: ArrayList<Genre>? = ArrayList(),
    var homepage: String? = "",
    var id: Int? = 0,
    var imdb_id: String? = "",
    var original_language: String? = "",
    var original_title: String? = "",
    var overview: String? = "",
    var popularity: Double? = 0.0,
    var poster_path: String? = "",
    var production_companies: ArrayList<ProductionCompany>? = ArrayList(),
    var production_countries: ArrayList<ProductionCountry>? = ArrayList(),
    var release_date: String? = "",
    var revenue: Int? = 0,
    var runtime: Int? = 0,
    var spoken_languages: ArrayList<SpokenLanguage>? = ArrayList(),
    var status: String? = "",
    var tagline: String? = "",
    var title: String? = "",
    var video: Boolean? = false,
    var vote_average: Double? = 0.0,
    var vote_count: Int? = 0
)