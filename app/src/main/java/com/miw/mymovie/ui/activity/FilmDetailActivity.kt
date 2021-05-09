package com.miw.mymovie.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.R
import com.miw.mymovie.databinding.ActivityFilmDetailBinding
import com.miw.mymovie.model.Film
import com.miw.mymovie.model.data.storage.local.Settings
import com.miw.mymovie.model.datasources.FilmProvider
import com.miw.mymovie.server.FilmServer
import kotlinx.coroutines.runBlocking
import java.security.Provider


class FilmDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "FilmDetailActivity::film"
    }

    private lateinit var binding: ActivityFilmDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initialize()
    }

    private fun initialize() {
        val imagebmp = { path: String ->
            runBlocking { FilmServer().getImageBMP(path) }
        }
        val voteCount = { count: Int -> "${getString(R.string.film_vote_count)}: $count" }
        val voteRate = { rate: Double -> "${getString(R.string.film_vote_rate)}: $rate" }
        intent.getParcelableExtra<Film>(EXTRA_FILM)?.apply {
            val apiFilm = obtainApiFilmInfo(this.idRemote)
            val persistedFilm = obtainPersistedFilmInfo(this.idRemote)
            if(persistedFilm != null){
                binding.swFav.isChecked = true
                binding.swSeen.isChecked = persistedFilm.seen
            } else {
            binding.swFav.isChecked = false
            binding.swSeen.isChecked = false
            }
            binding.imgFilm.setImageBitmap(imagebmp(apiFilm.image))
            if(!binding.swFav.isChecked) {
                binding.swSeen.isEnabled = false
            }
            binding.txTitle.text = apiFilm.title
            binding.txOverview.text = apiFilm.overview
            binding.txVoteCount.text = voteCount(apiFilm.voteCount)
            binding.txVoteRate.text = voteRate(apiFilm.voteAverage)
            binding.swFav.setOnClickListener{handleFavClick(this)}
            binding.swSeen.setOnClickListener{handleSeenClick(this)}
        }
    }

    private fun obtainApiFilmInfo(remoteId: Long): Film {
        return runBlocking { FilmServer().getMovie(remoteId) }
    }

    private fun obtainPersistedFilmInfo(remoteId: Long): Film? {
        val username = Settings(this@FilmDetailActivity).login!!

        val film = FilmProvider.requestFilmsUsers(username).filmList
            .filter { film -> film.idRemote == remoteId }.toList()
        if (film.isEmpty())
            return null

        return film.last()
    }

    private fun handleFavClick(film: Film) {
        val username =Settings(this@FilmDetailActivity).login!!
        val toAdd = Film(
            idRemote = film.idRemote,
            image = film.image,
            overview = film.overview,
            voteAverage = binding.txVoteRate.text.split(": ")[1].toDouble(),
            voteCount = binding.txVoteCount.text.split(": ")[1].toInt(),
            seen = binding.swSeen.isChecked,
            title = film.title,
            username = username,
            originalTitle = film.originalTitle
        )
       if (binding.swFav.isChecked) {
           FilmProvider.saveFavoriteFilm(toAdd)
           binding.swSeen.isEnabled = true
       } else {
           FilmProvider.deleteFavoriteFilm(toAdd)
           binding.swSeen.isChecked = false
           binding.swSeen.isEnabled = false
       }
    }


    private fun handleSeenClick(film: Film) {
        val username =Settings(this@FilmDetailActivity).login!!
        val updated = Film(
            idRemote = film.idRemote,
            image = film.image,
            overview = film.overview,
            voteAverage = binding.txVoteRate.text.split(": ")[1].toDouble(),
            voteCount = binding.txVoteCount.text.split(": ")[1].toInt(),
            seen = binding.swSeen.isChecked,
            title = film.title,
            username = username,
            originalTitle = film.originalTitle
        )

        FilmProvider.updateFavouriteFilm(updated)
    }


}