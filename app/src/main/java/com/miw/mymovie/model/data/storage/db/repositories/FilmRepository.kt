package com.miw.mymovie.model.data.storage.db.repositories

import com.miw.mymovie.model.Film
import com.miw.mymovie.model.User
import com.miw.mymovie.model.data.storage.db.MyDatabase
import com.miw.mymovie.model.data.storage.db.entities.FilmEntity
import com.miw.mymovie.model.data.storage.db.mappers.FilmMapper

class FilmRepository {
    private val filmDao = MyDatabase.instance.filmDao();

    fun requestFilmsUsers(username: String): List<Film?> {
        val films = filmDao.getAll(username)
        return films.map {
            FilmMapper.convertToDomain(it)
        }
    }

    fun deleteFavoriteFilm(film: Film) {
        filmDao.delete(FilmMapper.convertFromDomain(film));
    }

    fun saveFavoriteFilm(film: Film) {
        //Save favorite book
        filmDao.insert(FilmMapper.convertFromDomain(film))
    }

    fun updateFavoriteFilm(film: Film) {
        //Save favorite book
        filmDao.update(FilmMapper.convertFromDomain(film))
    }

}