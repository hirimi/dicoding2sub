package com.example.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows_entity")
data class TvShowsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tvshows_id")
    var id: Int? = null,

    @ColumnInfo(name = "tvshows_poster")
    var poster: String? = null,

    @ColumnInfo(name = "tvshows_name")
    var tvShowsName: String? = null,

    @ColumnInfo(name = "tvshows_description")
    var description: String? = null,

    @ColumnInfo(name = "tvshows_releasedate")
    var releasedate: String? = null,

    @ColumnInfo(name = "tvshows_rate")
    var rate: Double? = null,

    @ColumnInfo(name = "tvshows_votecount")
    var votecount: Int? = null,

    @ColumnInfo(name = "tvshows_setfavorite")
    var setFavorite: Boolean = false
)