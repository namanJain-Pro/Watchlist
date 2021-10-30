package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class Title(
    val id: String,
    val title: String,
    val fullTitle: String,
    val type: String,
    val year: Int,
    @SerializedName("image") val imageURL: String,
    val releaseDate: String,
    @SerializedName("runtimeStr") val runtime: String,
    val plot: String,
    val directorList: List<CommonEntity>,
    val starList: List<CommonEntity>,
    val actorList: List<Actor>,
    val genres: String,
    val imDbRating: Float
)
