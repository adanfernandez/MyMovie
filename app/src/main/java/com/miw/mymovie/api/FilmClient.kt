package com.miw.mymovie.api

import com.google.gson.Gson
import com.miw.mymovie.R
import com.mymovie.model.Film
import java.net.URL

class FilmClient {

    fun getLatestFilms(): List<Film> {
        var filmList = emptyList<Film>();
        val jsonFilmList =
            URL("${R.string.api_url}${R.string.api_latest}?api_key=${R.string.api_key}")
                .readText()


        return filmList
    }
}