package com.example.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.entity.MovieEntity
import com.example.core.entity.TvShowsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    //Movies
    @Query("SELECT * FROM movie_entity")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE movie_setfavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntities: List<MovieEntity>)

    @Update
    fun updateMovies(movieEntities: MovieEntity)

    //TvShows
    @Query("SELECT * FROM tv_shows_entity")
    fun getTvShows(): Flow<List<TvShowsEntity>>

    @Query("SELECT * FROM tv_shows_entity WHERE tvshows_setfavorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShowsEntities: List<TvShowsEntity>)

    @Update
    fun updateTvShows(tvShowsEntities: TvShowsEntity)

}