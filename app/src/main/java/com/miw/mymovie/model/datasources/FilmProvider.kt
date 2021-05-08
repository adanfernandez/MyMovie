package com.miw.mymovie.model.datasources
import com.miw.mymovie.model.Film
import com.miw.mymovie.model.data.storage.db.mappers.FilmMapper
import com.miw.mymovie.model.data.storage.db.repositories.FilmRepository

object FilmProvider {
    private val filmRepository: FilmRepository = FilmRepository()

    fun requestFilmsUsers(username: String): List<Film?> {
        return filmRepository.requestFilmsUsers(username);
    }

    fun deleteFavoriteFilm(film: Film) {
        return filmRepository.deleteFavoriteFilm(film)
    }

    fun saveFavoriteFilm(film: Film) {
        return filmRepository.saveFavoriteFilm(film)
    }
}