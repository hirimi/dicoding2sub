package com.example.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.entity.MovieEntity
import com.example.core.entity.TvShowsEntity

@Database(entities = [MovieEntity::class, TvShowsEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}