package com.miw.mymovie.model;


import android.os.Parcelable
import com.miw.mymovie.server.FilmServer
import kotlinx.parcelize.Parcelize


data class LatestFilmList(val latestFilmList: List<Film>) {
    companion object {
        suspend fun getLatest() :LatestFilmList  {
            return FilmServer().getLatestFilms()
        }
    }
}

@Parcelize
class Film(
    val idRemote: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val image: String,
    val voteCount: Int,
    val voteAverage: Double,
    val seen: Boolean,
    val username: String = ""
) : Parcelable {
}