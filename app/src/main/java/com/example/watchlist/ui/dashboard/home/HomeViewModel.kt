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

    val responseList: MutableLiveData<List<ExploreItem>> = MutableLiveData()

    fun fetch(type: String) {
        Log.d(TAG, "fetch: $type")
        viewModelScope.launch {
            val response = repository.others(type)

            if (response.isSuccessful) {
                responseList.value = response.body()?.items
                Log.d(TAG, "fetch: list fetched")
            } else {
                Log.d(TAG, "fetch: ${response.message()}")
            }
        }
    }

}