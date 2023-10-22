package com.example.core.utils

import com.example.core.domain.model.Movies
import com.example.core.domain.model.TvShows
import com.example.core.entity.MovieEntity
import com.example.core.entity.TvShowsEntity
import com.example.core.response.movie.MovieDetailResponse
import com.example.core.response.tv.TvShowsDetailResponse

object DataMapper {
    //MOVIES
    fun mapMovieResponsesToEntities(input: List<MovieDetailResponse>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                id = it.id,
                poster = it.poster,
                movieName = it.movieName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                poster = it.poster,
                movieName = it.movieName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount,
                setFavorite = it.setFavorite
            )
        }

    fun mapMovieDomainToEntities(input: Movies) =
        MovieEntity(
            id = input.id,
            poster = input.poster,
            movieName = input.movieName,
            description = input.description,
            releasedate = input.releasedate,
            rate = input.rate,
            votecount = input.votecount,
            setFavorite = input.setFavorite
        )

    //TV SHOWS
    fun mapTvShowsResponsesToEntites(input: List<TvShowsDetailResponse>): List<TvShowsEntity>{
        val tvShowsList = ArrayList<TvShowsEntity>()
        input.map {
            val tvShows = TvShowsEntity(
                id = it.id,
                poster = it.poster,
                tvShowsName = it.tvShowsName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount
            )
            tvShowsList.add(tvShows)
        }
        return tvShowsList
    }

    fun mapTvShowsEntitiesToDomain(input: List<TvShowsEntity>): List<TvShows> =
        input.map {
            TvShows(
                id = it.id,
                poster = it.poster,
                tvShowsName = it.tvShowsName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount,
                setFavorite = it.setFavorite
            )
        }

    fun mapTvShowsDomainToEntities(input: TvShows) =
        TvShowsEntity(
            id = input.id,
            poster = input.poster,
            tvShowsName = input.tvShowsName,
            description = input.description,
            releasedate = input.releasedate,
            rate = input.rate,
            votecount = input.votecount,
            setFavorite = input.setFavorite
        )
}