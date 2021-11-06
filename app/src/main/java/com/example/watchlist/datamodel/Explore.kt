package com.example.watchlist.datamodel

//This entity can be used in multiple types like Top250Movies, Top250Shows

data class Explore(
    val items: List<ExploreItem>
)