package com.example.favorite.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvFavoriteViewModel @Inject constructor(mainUseCase: MainUseCase): ViewModel() {
    val favoriteTvShows = mainUseCase.getFavoriteTvShows().asLiveData()
}