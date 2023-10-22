package com.example.movie.tabtv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(mainUseCase: MainUseCase): ViewModel() {
    val tvShows = mainUseCase.getTvShows().asLiveData()
}