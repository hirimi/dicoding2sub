package com.example.core.retrofit

import com.example.core.BuildConfig.API_KEY
import com.example.core.response.movie.MovieResponse
import com.example.core.response.tv.TvShowsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=${API_KEY}")
    suspend fun getMovie(): MovieResponse

    @GET("tv/popular?api_key=${API_KEY}")
    suspend fun getTvShows(): TvShowsResponse
}
