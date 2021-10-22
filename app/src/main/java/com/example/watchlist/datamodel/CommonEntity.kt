package com.example.watchlist.datamodel

// This common entity is used for type which only have id and name like director, stars

data class CommonEntity (
    private val id: String,
    private val name: String
)