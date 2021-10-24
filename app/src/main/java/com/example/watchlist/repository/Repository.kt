package com.example.watchlist.repository

import com.example.watchlist.Constants.Companion.API_KEY
import com.example.watchlist.datamodel.ExploreItem
import com.example.watchlist.datamodel.SearchResult
import com.example.watchlist.datamodel.Title
import com.example.watchlist.remoteapi.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun search(expression: String) : List<SearchResult> {
        return RetrofitInstance.api.search(API_KEY, expression)
    }

    suspend fun title(id: String) : Response<Title> {
        return RetrofitInstance.api.title(API_KEY, id)
    }

    suspend fun topMovies() : List<ExploreItem> {
        return RetrofitInstance.api.topMovies(API_KEY)
    }

    suspend fun topTVShows() : List<ExploreItem> {
        return RetrofitInstance.api.topTVShows(API_KEY)
    }
}