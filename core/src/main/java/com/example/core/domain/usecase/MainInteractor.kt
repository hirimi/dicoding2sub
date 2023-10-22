package com.example.core.domain.usecase

import com.example.core.domain.model.Movies
import com.example.core.domain.model.TvShows
import com.example.core.domain.repository.IMainRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(private val repository: IMainRepository) : MainUseCase {
    override fun getMovies() = repository.getMovies()

    override fun getFavoriteMovies() = repository.getFavoriteMovies()

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        return repository.setFavoriteMovies(movies, state)
    }

    override fun getTvShows() = repository.getTvShows()

    override fun getFavoriteTvShows() = repository.getFavoriteTvShows()

    override fun setFavoriteTvShows(tvShows: TvShows, state: Boolean) {
        return repository.setFavoriteTvShows(tvShows, state)
    }
}