package com.example.favorite.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.MainUseCase
import com.example.favorite.movie.MovieFavoriteViewModel
import com.example.favorite.tv.TvFavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mainUseCase: MainUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(mainUseCase) as T
            }
            modelClass.isAssignableFrom(TvFavoriteViewModel::class.java) -> {
                TvFavoriteViewModel(mainUseCase) as T
            }
            else -> throw Throwable("Unkwon ViewModel Class: ${modelClass.name}")
        }
}