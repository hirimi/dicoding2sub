package com.example.core.response.tv

import com.google.gson.annotations.SerializedName

data class TvShowsDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("original_name")
    val tvShowsName: String,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("first_air_date")
    val releasedate: String,

    @field:SerializedName("vote_average")
    val rate: Double,

    @field:SerializedName("vote_count")
    val votecount: Int
)
