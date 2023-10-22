package com.example.movie.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Movies
import com.example.core.domain.model.TvShows
import com.example.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val mainUseCase: MainUseCase): ViewModel() {

    fun setFavoriteMovies(movies: Movies, state: Boolean) = mainUseCase.setFavoriteMovies(movies, state)

    fun setFavoriteTvShows(tvShows: TvShows, state: Boolean) = mainUseCase.setFavoriteTvShows(tvShows, state)
}