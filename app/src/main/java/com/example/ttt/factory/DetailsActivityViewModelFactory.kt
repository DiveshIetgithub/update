package com.example.ttt.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ttt.model.DetailsActivtyViewModel
import com.example.ttt.repository.SqliteDBRepository


class DetailsActivityViewModelFactory(private val repository: SqliteDBRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsActivtyViewModel::class.java)){
            return DetailsActivtyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}