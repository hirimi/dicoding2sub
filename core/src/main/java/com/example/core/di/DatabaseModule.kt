package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.room.FilmDao
import com.example.core.room.FilmDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FilmDatabase = Room.databaseBuilder(
        context,
        FilmDatabase::class.java, "favorites.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieKuFavoritesDao(database: FilmDatabase): FilmDao =
        database.filmDao()

}