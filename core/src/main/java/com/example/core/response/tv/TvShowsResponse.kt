package com.example.core.response.tv

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("results")
    val result: List<TvShowsDetailResponse>
)
