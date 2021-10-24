package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class Actor (
    val id: String,
    @SerializedName("image") val imageURL: String,
    val name: String,
    val asCharacter: String
)
