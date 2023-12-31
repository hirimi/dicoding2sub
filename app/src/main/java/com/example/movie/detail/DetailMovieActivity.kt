package com.example.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.core.BuildConfig.BASE_IMAGE
import com.example.core.domain.model.Movies
import com.example.movie.R
import com.example.movie.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private val detailViewModel: DetailViewModel by viewModels()

    companion object{
        const val MOVIE = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detailMovies = intent.getParcelableExtra<Movies>(MOVIE)
        if (detailMovies != null){
            getMovies(detailMovies)
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

    private fun getMovies(movies: Movies){
        Glide.with(this)
            .load(BASE_IMAGE + movies.poster)
            .override(250, 320)
            .into(binding.ivPoster)
        binding.tvMovie.text = movies.movieName
        binding.movieRelease.text = movies.releasedate
        binding.movieRate.text = movies.rate.toString()
        binding.movieVotecount.text = movies.votecount.toString()
        binding.tvDes.text = movies.description

        var isFavorite = movies.setFavorite
        setFavorite(isFavorite)
        binding.setFavorite.setOnClickListener {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteMovies(movies, isFavorite)
            setFavorite(isFavorite)

            if (isFavorite){
                Toast.makeText(this, "Added to Favorite Movies", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Removed from Favorite Movies", Toast.LENGTH_SHORT).show()
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

}