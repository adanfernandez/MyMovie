package com.miw.mymovie.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.miw.mymovie.databinding.FragmentLatestBinding
import com.miw.mymovie.ui.adapters.FilmListAdapter
import com.miw.mymovie.ui.viewmodels.FilmsViewModel

class LatestFragment : Fragment() {

    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: OnLatestFragmentInteractionListener

    private val viewModel: FilmsViewModel = FilmsViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLatestFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context debe implementar OnLatestFragmentInteractionListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        binding.latestList.layoutManager = LinearLayoutManager(context)
        viewModel.filmList.observe(this) { items ->
            binding.latestList.adapter = FilmListAdapter(items.filmList) {
                startActivity(Intent(context, FilmDetailActivity::class.java).apply {
                    putExtra(FilmDetailActivity.EXTRA_FILM, it)
                })
            }
        }

        viewModel.requestFilms()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnLatestFragmentInteractionListener {
        fun onNextButtonClick()
    }
}