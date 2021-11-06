package com.example.watchlist.ui.dashboard.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.datamodel.Title
import com.example.watchlist.repository.Repository
import kotlinx.coroutines.launch

private const val TAG = "CommonViewModel"

class CommonViewModel(private val repository: Repository): ViewModel() {

    val title: MutableLiveData<Title> = MutableLiveData()

    fun fetchTitle(id: String) {
        viewModelScope.launch {
            val response = repository.title(id)

            if (response.isSuccessful) {
                title.value = response.body()
            } else {
                Log.d(TAG, "fetchTitle: ${response.message()}")
            }
        }
    }
}