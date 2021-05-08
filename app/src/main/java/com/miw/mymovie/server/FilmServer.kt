package com.miw.mymovie.server

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.google.gson.Gson
import com.miw.mymovie.model.Film
import com.miw.mymovie.model.LatestFilmList
import com.miw.mymovie.server.constants.API_EP_LATEST
import com.miw.mymovie.server.constants.API_KEY
import com.miw.mymovie.server.constants.API_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL


class FilmServer {


    suspend fun getLatestFilms(): LatestFilmList {
        //val policy = ThreadPolicy.Builder().permitAll().build()
        //StrictMode.setThreadPolicy(policy)
        return withContext(Dispatchers.IO) {

            val jsonFilmList =
                URL("${API_URL}${API_EP_LATEST}?api_key=${API_KEY}&language=es-ES")
                    .readText()

            val result = Gson().fromJson(jsonFilmList, FilmResult::class.java)

            FilmDataMapper.convertToModel(result)
        }

    }

    private fun getRes(key: String) {

    }
}