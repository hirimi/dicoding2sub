package com.example.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.BuildConfig.BASE_IMAGE
import com.example.core.R
import com.example.core.databinding.ItemRowBinding
import com.example.core.domain.model.Movies

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var listFavoriteMovies = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(favoriteMovieList: List<Movies>?){
        if (favoriteMovieList == null) return
        listFavoriteMovies.clear()
        listFavoriteMovies.addAll(favoriteMovieList)
        notifyDataSetChanged()
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowBinding.bind(itemView)

        fun bind(movies: Movies){
            with(binding){
                Glide.with(itemView)
                    .load(BASE_IMAGE + movies.poster)
                    .override(150,220)
                    .into(ivPoster)
                tvFavorites.text = movies.movieName
                tvRate.text = movies.rate.toString()
                tvDes.text = movies.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listFavoriteMovies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movieFavorites = listFavoriteMovies[position]
        holder.bind(movieFavorites)
    }

    override fun getItemCount() = listFavoriteMovies.size

}