package com.example.watchlist.remoteapi

import com.example.watchlist.datamodel.Explore
import com.example.watchlist.datamodel.SearchResult
import com.example.watchlist.datamodel.Title
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDBApi {

    @GET("Search/{api_key}/{expression}")
    suspend fun search(
        @Path("api_key") key: String,
        @Path("expression") exp: String
    ) : Response<SearchResult>

    @GET("Title/{api_key}/{id}")
    suspend fun title(
        @Path("api_key") key: String,
        @Path("id") id: String
    ) : Response<Title>

    @GET("{type}/{api_key}")
    suspend fun others(
        @Path("type") type: String,
        @Path("api_key") key: String
    ) : Response<Explore>
}