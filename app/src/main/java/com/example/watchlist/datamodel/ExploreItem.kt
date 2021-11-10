package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class ExploreItem(
    val id: String,
    val title: String,
    @SerializedName("image") val imageURL: String,
    val imDbRating: Float
)