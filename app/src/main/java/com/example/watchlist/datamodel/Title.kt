package com.example.watchlist.datamodel

import com.google.gson.annotations.SerializedName

data class Title(
    private val id: String,
    private val title: String,
    private val fullTitle: String,
    private val type: String,
    private val year: Int,
    @SerializedName("image") private val imageURL: String,
    private val releaseDate: String,
    @SerializedName("runtimeStr") private val runtime: String,
    private val plot: String,
    private val directorList: List<CommonEntity>,
    private val starList: List<CommonEntity>,
    private val actorList: List<Actor>,
    private val genres: String
)
