package com.example.core.response.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val result: List<MovieDetailResponse>
):Parcelable
