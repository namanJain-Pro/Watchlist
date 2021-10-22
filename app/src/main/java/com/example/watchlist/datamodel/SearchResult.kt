package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class SearchResult (
    private val id: String,
    private val resultType: String,
    @SerializedName("image") private val imageURL: String ,
    private val title: String,
    private val description: String
)