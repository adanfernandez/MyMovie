package com.miw.mymovie.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miw.mymovie.R
import com.miw.mymovie.databinding.FragmentLatestBinding
import com.miw.mymovie.server.FilmServer
import java.lang.RuntimeException

class LatestFragment : Fragment() {

    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: OnLatestFragmentInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLatestFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context debe implementar OnLatestFragmentInteractionListener")
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        loadLatest()
        return binding.root
    }

    fun loadLatest(){
       binding.latestList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnLatestFragmentInteractionListener {
        fun onNextButtonClick()
    }
}