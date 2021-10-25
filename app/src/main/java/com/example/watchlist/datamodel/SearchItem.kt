package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class SearchItem (
    val id: String,
    val resultType: String,
    @SerializedName("image") val imageURL: String,
    val title: String,
    val description: String
)