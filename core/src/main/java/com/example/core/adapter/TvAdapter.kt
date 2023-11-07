package com.example.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.BuildConfig.BASE_IMAGE
import com.example.core.R
import com.example.core.databinding.ItemRowBinding
import com.example.core.domain.model.TvShows

class TvAdapter: RecyclerView.Adapter<TvAdapter.TvShowsHolder>() {

    private var listFavoriteTvShows = ArrayList<TvShows>()
    var onItemClick: ((TvShows) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(favoriteTvShows: List<TvShows>?){
        if (favoriteTvShows == null) return
        listFavoriteTvShows.clear()
        listFavoriteTvShows.addAll(favoriteTvShows)
        notifyDataSetChanged()
    }

    inner class TvShowsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowBinding.bind(itemView)

        fun bind(tvShows: TvShows){
            with(binding){
                Glide.with(itemView)
                    .load(BASE_IMAGE + tvShows.poster)
                    .override(150,220)
                    .into(ivPoster)
                tvFavorites.text = tvShows.tvShowsName
                tvRate.text = tvShows.rate.toString()
                tvDes.text = tvShows.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listFavoriteTvShows[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TvShowsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))

    override fun onBindViewHolder(holder: TvShowsHolder, position: Int) {
        val tvShowsFavorites = listFavoriteTvShows[position]
        holder.bind(tvShowsFavorites)
    }

    override fun getItemCount() = listFavoriteTvShows.size

}