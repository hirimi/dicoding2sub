package com.example.favorite.movie

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
import com.example.favorite.databinding.FragmentMovieFavoriteBinding
import com.example.favorite.util.ViewModelFactory
import com.example.favorite.di.DaggerFavoritesComponent
import com.example.movie.detail.DetailMovieActivity
import com.example.movie.di.FavoritesDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MovieFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentMovieFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoritesMovieViewModel: MovieFavoriteViewModel by viewModels {factory}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoritesComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoritesDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val favoriteMovieAdapter = MovieAdapter()
            favoriteMovieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.MOVIE, it)
                startActivity(intent)
            }

            favoritesMovieViewModel.favoriteMovies.observe(viewLifecycleOwner) { listMovie ->
                favoriteMovieAdapter.setData(listMovie)
                if (listMovie.isEmpty()) {
                    Toast.makeText(context, "Favorite Movies is Empty!", Toast.LENGTH_SHORT).show()
                }
            }


            with(binding.rvFavoritesMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }
    }

}