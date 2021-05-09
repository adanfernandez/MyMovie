package com.miw.mymovie.server

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.miw.mymovie.model.Film
import com.miw.mymovie.model.FilmList
import com.miw.mymovie.server.constants.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL


class FilmServer {


    suspend fun getLatestFilms(): FilmList {
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

    suspend fun getImageBMP(path: String): Bitmap {
        return withContext(Dispatchers.IO) {
            BitmapFactory.decodeStream(
                URL("$API_IMAGE_URL$path").openConnection().getInputStream()
            )
        }
    }

    suspend fun getMovie(remoteId: Long): Film {
        return withContext(Dispatchers.IO) {
            val jsonFilm =
                URL("${API_URL}${API_EP_MOVIE}/$remoteId?api_key=${API_KEY}&language=es-ES")
                    .readText()
            val result = Gson().fromJson(jsonFilm, FilmObject::class.java)
            FilmDataMapper.convertOneToModel(result)
        }
    }
}