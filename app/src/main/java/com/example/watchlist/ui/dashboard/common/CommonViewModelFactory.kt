package com.example.watchlist.ui.dashboard.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchlist.repository.Repository

class CommonViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommonViewModel(repository) as T
    }
}