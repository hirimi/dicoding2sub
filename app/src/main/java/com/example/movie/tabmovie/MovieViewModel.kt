package com.example.movie.tabmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class
MovieViewModel @Inject constructor(mainUseCase: MainUseCase): ViewModel() {
    val movies = mainUseCase.getMovies().asLiveData()
}