package com.miw.mymovie.model.datasources
import com.miw.mymovie.model.data.storage.db.repositories.FilmRepository

object FilmProvider {
    private val filmRepository: FilmRepository = FilmRepository()
}