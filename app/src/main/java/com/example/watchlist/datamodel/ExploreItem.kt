package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

//This entity can be used in multiple types like Top250Movies, Top250Shows

data class ExploreItem (
    val id: String,
    val title: String,
    @SerializedName("image") val imageURL: String,
    val imDbRating: Float
)