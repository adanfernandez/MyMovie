package com.miw.mymovie.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.miw.mymovie.databinding.FragmentFavouritesBinding
import com.miw.mymovie.model.data.storage.local.Settings
import com.miw.mymovie.ui.adapters.FilmListAdapter
import com.miw.mymovie.ui.viewmodels.FilmsViewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: OnFavouritesFragmentInteractionListener

    private val viewModel: FilmsViewModel = FilmsViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFavouritesFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context debe implementar OnFavouritesFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        binding.favouritesList.layoutManager = LinearLayoutManager(context)
        viewModel.filmList.observe(this) { items ->
            binding.favouritesList.adapter = FilmListAdapter(items.filmList) {
                startActivity(Intent(context, FilmDetailActivity::class.java).apply {
                    putExtra(FilmDetailActivity.EXTRA_FILM, it)
                })
            }
        }

        val username = Settings(context!!).login!!
        viewModel.obtainFilms(username)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    interface OnFavouritesFragmentInteractionListener {
        fun onBackButtonClick()
    }
}