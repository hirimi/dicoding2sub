package com.example.movie.di

import com.example.core.domain.usecase.MainUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoritesDependencies {
    fun mainUseCase(): MainUseCase
}