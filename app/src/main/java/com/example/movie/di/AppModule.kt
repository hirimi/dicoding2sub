package com.example.movie.di

import com.example.core.domain.usecase.MainInteractor
import com.example.core.domain.usecase.MainUseCase
import com.example.core.response.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieKuUseCase(repository: Repository): MainUseCase =
        MainInteractor(repository)

}