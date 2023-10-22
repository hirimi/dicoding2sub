package com.example.core.response

import com.example.core.entity.MovieEntity
import com.example.core.entity.TvShowsEntity
import com.example.core.room.FilmDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: FilmDao){

    //Movies
    fun getAllMovies(): Flow<List<MovieEntity>> = dao.getMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = dao.getFavoriteMovies()

    suspend fun insertMovies(movieEntities: List<MovieEntity>) = dao.insertMovies(movieEntities)

    fun setFavoriteMovies(movieEntities: MovieEntity, newState: Boolean){
        movieEntities.setFavorite = newState
        dao.updateMovies(movieEntities)
    }

    //TvShows
    fun getAlltTvShows(): Flow<List<TvShowsEntity>> = dao.getTvShows()

    fun getFavoriteTvShows(): Flow<List<TvShowsEntity>> = dao.getFavoriteTvShows()

    suspend fun insertTvShows(tvShowsEntities: List<TvShowsEntity>) = dao.insertTvShows(tvShowsEntities)

    fun setFavoriteTvShows(tvShowsEntities: TvShowsEntity, newState: Boolean){
        tvShowsEntities.setFavorite = newState
        dao.updateTvShows(tvShowsEntities)
    }
}