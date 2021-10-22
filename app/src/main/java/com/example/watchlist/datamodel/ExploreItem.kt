package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

//This entity can be used in multiple types like Top250Movies, Top250Shows

data class ExploreItem (
    private val id: String,
    private val rank: Int,
    private val title: String,
    @SerializedName("image") private val imageURL: String,
    private val year: String,
    private val imDbRating: Float
)