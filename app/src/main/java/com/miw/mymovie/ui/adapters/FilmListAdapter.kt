package com.miw.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.miw.mymovie.R
import com.miw.mymovie.databinding.CardFilmItemBinding
import com.miw.mymovie.model.Film

class FilmListAdapter(val items: List<Film>?, private val itemClick: (Film) -> Unit) : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {

    inner class ViewHolder(cardView: CardView, val itemClick: (Film) -> Unit) :
        RecyclerView.ViewHolder(cardView) {


        fun bindFilm(film: Film) {
            val binding = CardFilmItemBinding.bind(itemView)
            val voteCount = {count: Int -> "${itemView.context.getString(R.string.film_vote_count)}: $count" }
            val voteRate = {rate: Double-> "${itemView.context.getString(R.string.film_vote_rate)}: $rate"}
            with(film) {
                binding.txFilmTitle.text = film.title
                binding.txVoteCount.text = voteCount(film.voteCount)
                binding.txVoteRate.text = voteRate(film.voteAverage)
                if (film.seen) {
                    binding.imgseen.visibility = View.VISIBLE
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_film_item, parent, false) as CardView

        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.let {
            holder.bindFilm(items[position])
        }
    }

    override fun getItemCount() = items.orEmpty().size
}