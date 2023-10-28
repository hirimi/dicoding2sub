package com.example.core.response.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("original_title")
    val movieName: String,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("release_date")
    val releasedate: String,

    @field:SerializedName("vote_average")
    val rate: Double,

    @field:SerializedName("vote_count")
    val votecount: Int
): Parcelable
