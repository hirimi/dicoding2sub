package com.example.favorite.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.adapter.TvAdapter
import com.example.favorite.di.DaggerFavoritesComponent
import com.example.favorite.databinding.FragmentTvFavoriteBinding
import com.example.favorite.util.ViewModelFactory
import com.example.movie.detail.DetailTvActivity
import com.example.movie.di.FavoritesDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class TvFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentTvFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoritesTvShowsViewModel: TvFavoriteViewModel by viewModels {factory}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvFavoriteBinding.inflate(inflater, container, false)
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
            val favoriteTvAdapter = TvAdapter()
            favoriteTvAdapter.onItemClick = {
                val intent = Intent(activity, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.TV_SH0WS, it)
                startActivity(intent)
            }

            favoritesTvShowsViewModel.favoriteTvShows.observe(viewLifecycleOwner) { listTvShows ->
                favoriteTvAdapter.setData(listTvShows)
                if (listTvShows.isEmpty()) {
                    Toast.makeText(context, "Favorite Tv Shows is Empty!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            with(binding.rvFavoritesTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvAdapter
            }
        }
    }
}