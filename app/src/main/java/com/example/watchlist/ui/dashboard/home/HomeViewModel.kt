package com.example.watchlist.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.datamodel.ExploreItem
import com.example.watchlist.repository.Repository
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val topMovies: MutableLiveData<List<ExploreItem>> = MutableLiveData()
    val topShows: MutableLiveData<List<ExploreItem>> = MutableLiveData()

    fun fetchMovies() {
        viewModelScope.launch {
            val response = repository.topMovies()

            if (response.isSuccessful) {
                topMovies.value = response.body()?.items
                Log.d(TAG, "fetchMovies: Movies fetched")
            } else {
                Log.d(TAG, "fetchMovies: ${response.message()}")
            }
        }
    }

    fun fetchShows() {
        viewModelScope.launch {
            val response = repository.topTVShows()

            if (response.isSuccessful) {
                topShows.value = response.body()?.items
                Log.d(TAG, "fetchShows: Shows fetched")
            } else {
                Log.d(TAG, "fetchShows: ${response.message()}")
            }
        }
    }

}