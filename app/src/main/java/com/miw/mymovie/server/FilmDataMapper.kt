package com.miw.mymovie.server

import com.miw.mymovie.model.Film
import com.miw.mymovie.model.FilmList

object FilmDataMapper {

    fun convertToModel(filmResult: FilmResult): FilmList =
       FilmList(convertToFilms(filmResult.results))


    private fun convertToFilms(list: List<FilmObject>): List<Film>{
        return list.map {film ->  Film(
            idRemote= film.id,
            title = film.title,
            originalTitle = film.title,
            overview = film.overview,
            image= film.posterPath,
            voteCount = film.voteCount,
            voteAverage = film.voteAverage,
            seen = false )}
    }

    fun convertOneToModel(film: FilmObject) : Film {
        return Film( idRemote= film.id,
            title = film.title,
            originalTitle = film.title,
            overview = film.overview,
            image= film.posterPath,
            voteCount = film.voteCount,
            voteAverage = film.voteAverage,
            seen = false
        )
    }
}