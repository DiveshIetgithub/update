package com.example.ttt.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ttt.model.SharedPrefRepositoryViewModel
import com.example.ttt.repository.SharedPrefRepository

class SharedPrefRepositoryViewModelFactory(private val sharedPrefRepo: SharedPrefRepository, private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedPrefRepositoryViewModel::class.java)){
            return SharedPrefRepositoryViewModel(sharedPrefRepo, context) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}