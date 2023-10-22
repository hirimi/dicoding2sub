package com.example.favorite.di

import android.content.Context
import com.example.favorite.movie.MovieFavoriteFragment
import com.example.favorite.tv.TvFavoriteFragment
import com.example.movie.di.FavoritesDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoritesDependencies::class])
interface FavoritesComponent {

    fun inject(favoriteMovieFragment: MovieFavoriteFragment)
    fun inject(favoriteTvShowsFragment: TvFavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoritesDependencies: FavoritesDependencies): Builder
        fun build(): FavoritesComponent
    }

}