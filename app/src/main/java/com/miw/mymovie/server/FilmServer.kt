package com.miw.mymovie.server

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.google.gson.Gson
import com.miw.mymovie.model.Film
import com.miw.mymovie.server.constants.API_EP_LATEST
import com.miw.mymovie.server.constants.API_KEY
import com.miw.mymovie.server.constants.API_URL
import java.net.URL


class FilmServer {


    fun getLatestFilms(): List<Film> {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        val jsonFilmList =
           URL("${API_URL}${API_EP_LATEST}?api_key=${API_KEY}&language=es-ES")
                .readText()

        val result = Gson().fromJson(jsonFilmList, FilmResult::class.java)

        return FilmDataMapper.convertToModel(result)

    }

    private fun getRes(key: String) {

    }
}