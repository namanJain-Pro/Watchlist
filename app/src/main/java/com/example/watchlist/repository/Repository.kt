package com.example.watchlist.repository

import com.example.watchlist.Constants.Companion.API_KEY
import com.example.watchlist.datamodel.Explore
import com.example.watchlist.datamodel.SearchResult
import com.example.watchlist.datamodel.Title
import com.example.watchlist.remoteapi.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun search(expression: String) : Response<SearchResult> {
        return RetrofitInstance.api.search(API_KEY, expression)
    }

    suspend fun title(id: String) : Response<Title> {
        return RetrofitInstance.api.title(API_KEY, id)
    }

    suspend fun topMovies() : Response<Explore> {
        return RetrofitInstance.api.topMovies(API_KEY)
    }

    suspend fun topTVShows() : Response<Explore> {
        return RetrofitInstance.api.topTVShows(API_KEY)
    }
}