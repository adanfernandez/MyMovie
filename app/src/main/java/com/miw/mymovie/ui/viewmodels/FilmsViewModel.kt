package com.miw.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miw.mymovie.model.LatestFilmList
import com.miw.mymovie.server.FilmServer
import kotlinx.coroutines.launch

class FilmsViewModel: ViewModel() {

    private val _filmList: MutableLiveData<LatestFilmList> by lazy {
        MutableLiveData<LatestFilmList>()
    }

    val filmList: LiveData<LatestFilmList> get() = _filmList

    fun requestFilms() {
        viewModelScope.launch {
            _filmList.value = FilmServer().getLatestFilms()
        }
    }
}