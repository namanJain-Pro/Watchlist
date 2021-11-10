package com.example.watchlist.remoteapi

import com.example.watchlist.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: IMDBApi by lazy {
        retrofit.create(IMDBApi::class.java)
    }
}