package com.miw.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miw.mymovie.model.FilmList
import com.miw.mymovie.model.datasources.FilmProvider
import com.miw.mymovie.server.FilmServer
import kotlinx.coroutines.launch

class FilmsViewModel: ViewModel() {

    private val _filmList: MutableLiveData<FilmList> by lazy {
        MutableLiveData<FilmList>()
    }

    val filmList: LiveData<FilmList> get() = _filmList

    fun requestFilms() {
        viewModelScope.launch {
            _filmList.value = FilmServer().getLatestFilms()
        }
    }

    fun obtainFilms(username: String) {
        viewModelScope.launch {
            _filmList.value = FilmProvider.requestFilmsUsers(username)
        }
    }
}