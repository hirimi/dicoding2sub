package com.example.core.response.tv

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowsResponse(
    @SerializedName("results")
    val result: List<TvShowsDetailResponse>
): Parcelable
