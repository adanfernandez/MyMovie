package com.miw.mymovie.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miw.mymovie.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: OnFavouritesFragmentInteractionListener

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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    interface OnFavouritesFragmentInteractionListener {
        fun onBackButtonClick()
    }
}