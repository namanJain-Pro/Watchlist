package com.example.watchlist.repository

import com.example.watchlist.datamodel.Explore
import com.example.watchlist.datamodel.SearchResult
import com.example.watchlist.datamodel.Title
import com.example.watchlist.remoteapi.RetrofitInstance
import com.example.watchlist.util.Constants.Companion.API_KEY
import retrofit2.Response

class Repository {

    suspend fun search(expression: String): Response<SearchResult> {
        return RetrofitInstance.api.search(API_KEY, expression)
    }

    suspend fun title(id: String): Response<Title> {
        return RetrofitInstance.api.title(API_KEY, id)
    }

    suspend fun others(type: String): Response<Explore> {
        return RetrofitInstance.api.others(type, API_KEY)
    }
}