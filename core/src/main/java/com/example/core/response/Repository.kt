package com.example.core.response

import com.example.core.domain.model.Movies
import com.example.core.domain.model.TvShows
import com.example.core.domain.repository.IMainRepository
import com.example.core.response.movie.MovieDetailResponse
import com.example.core.response.tv.TvShowsDetailResponse
import com.example.core.source.RemoteDataSource
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import com.example.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSources: LocalDataSource,
    private val appExecutors: AppExecutors
): IMainRepository {

    override fun getMovies(): Flow<Resource<List<Movies>>> {
        return object : NetworkBoundResource<List<Movies>, List<MovieDetailResponse>>(){
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSources.getAllMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDetailResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieDetailResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSources.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSources.getFavoriteMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        val movieEntities = DataMapper.mapMovieDomainToEntities(movies)
        appExecutors.diskIO().execute {
            localDataSources.setFavoriteMovies(movieEntities, state)
        }
    }

    override fun getTvShows(): Flow<Resource<List<TvShows>>> {
        return object : NetworkBoundResource<List<TvShows>, List<TvShowsDetailResponse>>(){
            public override fun loadFromDB(): Flow<List<TvShows>> {
                return localDataSources.getAlltTvShows().map {
                    DataMapper.mapTvShowsEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShows>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowsDetailResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowsDetailResponse>) {
                val tvShowsList = DataMapper.mapTvShowsResponsesToEntites(data)
                localDataSources.insertTvShows(tvShowsList)
            }
        }.asFlow()
    }

    override fun getFavoriteTvShows(): Flow<List<TvShows>> {
        return localDataSources.getFavoriteTvShows().map{
            DataMapper.mapTvShowsEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTvShows(tvShows: TvShows, state: Boolean) {
        val tvShowsEntities = DataMapper.mapTvShowsDomainToEntities(tvShows)
        appExecutors.diskIO().execute {
            localDataSources.setFavoriteTvShows(tvShowsEntities, state)
        }
    }
}