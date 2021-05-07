package com.miw.mymovie.server

import com.google.gson.annotations.SerializedName

data class FilmResult(val results: List<FilmObject>);

data class FilmObject(
    val id: Long,
    val overview: String,
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("genre_ids") val genreIds: List<Int>
    )