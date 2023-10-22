package com.example.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.core.BuildConfig.BASE_IMAGE
import com.example.core.domain.model.TvShows
import com.example.movie.R
import com.example.movie.databinding.ActivityDetailTvBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvBinding

    private val detailViewModel: DetailViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detailTvShows = intent.getParcelableExtra<TvShows>(TV_SH0WS)

        if (detailTvShows != null){
            getTvShows(detailTvShows)
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        } else{
            binding.progressbar.visibility = View.GONE
        }
    }

    private fun getTvShows(tvShows: TvShows){
        Glide.with(this)
            .load(BASE_IMAGE + tvShows.poster)
            .override(250, 320)
            .into(binding.ivPosterTvshows)
        binding.tvshowsRelease.text = tvShows.releasedate
        binding.tvshowsRate.text = tvShows.rate.toString()
        binding.tvshowsVotecount.text = tvShows.votecount.toString()
        binding.tvDesTvshows.text = tvShows.description
        binding.tvTvshows.text = tvShows.tvShowsName

        var isFavorite = tvShows.setFavorite
        setFavorite(isFavorite)

        binding.setFavorite.setOnClickListener {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteTvShows(tvShows, isFavorite)
            setFavorite(isFavorite)

            if (isFavorite){
                Toast.makeText(this, "Added to Favorite Tv Shows", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Removed from Favorite Tv Shows", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavorite(state: Boolean){
        if (state){
            binding.setFavorite.setImageResource(R.drawable.ic_set_favorite)
        } else{
            binding.setFavorite.setImageResource(R.drawable.ic_no_favorite)
        }
    }

    companion object{
        const val TV_SH0WS = "tv_show"
    }
}