package com.miw.mymovie.model.data.storage.db.mappers

import com.miw.mymovie.model.Film
import com.miw.mymovie.model.data.storage.db.entities.FilmEntity

object FilmMapper  {
    fun convertToDomain(film: FilmEntity?) = convertFilmToDomain(film)

    private fun convertFilmToDomain(film: FilmEntity?): Film? {
        if(film != null) {
            return Film(idRemote=film.id_remote,title=film.title, username=film.username,seen=film.seen, image="", originalTitle = "", overview = "", voteAverage = 0.0, voteCount = 0)
        }
        return null
    }

    fun convertFromDomain(film: Film) = convertFilmFromDomain(film)

    private fun convertFilmFromDomain(film: Film): FilmEntity? {
        if(film != null) {
            return FilmEntity(
                title = film.title,
                username = film.username,
                seen = film.seen,
                id_remote = film.idRemote
            )
        }
        return null
    }
}