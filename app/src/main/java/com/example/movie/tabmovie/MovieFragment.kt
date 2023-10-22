package com.example.movie.tabmovie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.adapter.MovieAdapter
import com.example.core.vo.Resource
import com.example.movie.R
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.detail.DetailMovieActivity
import com.example.movie.detail.DetailMovieActivity.Companion.MOVIE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(MOVIE, it)
                startActivity(intent)
            }

            movieViewModel.movies.observe(viewLifecycleOwner) { listMovies ->
                if (listMovies != null) {
                    when (listMovies) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            movieAdapter.setData(listMovies.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            Toast.makeText(
                                context,
                                "Kesalahan Ketika Memuat Data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            with(fragmentMovieBinding.rvMovie) {
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = movieAdapter
            }
        }
    }


    private fun showLoading(state: Boolean){
        if (state){
            fragmentMovieBinding.progressMovies.visibility = View.VISIBLE
        } else{
            fragmentMovieBinding.progressMovies.visibility = View.GONE
        }
    }
}