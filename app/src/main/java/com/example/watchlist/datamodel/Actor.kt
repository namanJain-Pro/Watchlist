package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class Actor (
    private val id: String,
    @SerializedName("image") private val imageURL: String,
    private val name: String,
    private val asCharacter: String
)
