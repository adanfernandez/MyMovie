package com.miw.mymovie.model.datasources
import com.miw.mymovie.model.Film
import com.miw.mymovie.model.FilmList
import com.miw.mymovie.model.data.storage.db.repositories.FilmRepository

object FilmProvider {
    private val filmRepository: FilmRepository = FilmRepository()

    fun requestFilmsUsers(username: String): FilmList {
        if (filmRepository.requestFilmsUsers(username).isEmpty())
            return FilmList(emptyList())

        return FilmList(filmRepository.requestFilmsUsers(username) as List<Film>)
    }

    fun deleteFavoriteFilm(film: Film) {
        return filmRepository.deleteFavoriteFilm(film)
    }

    fun saveFavoriteFilm(film: Film) {
        return filmRepository.saveFavoriteFilm(film)
    }
}