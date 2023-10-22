package com.example.core.domain.usecase

import com.example.core.domain.model.Movies
import com.example.core.domain.model.TvShows
import com.example.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    //MOVIES
    fun getMovies(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies, state: Boolean)

    //TVSHOWS
    fun getTvShows(): Flow<Resource<List<TvShows>>>
    fun getFavoriteTvShows(): Flow<List<TvShows>>
    fun setFavoriteTvShows(tvShows: TvShows, state: Boolean)
}