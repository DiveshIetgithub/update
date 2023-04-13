package com.example.ttt.repository


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


private const val FILE_NAME: String = "_prefs"
private const val MODE = MODE_PRIVATE

object SharedPrefRepository {
    fun getPreference(context: Context): SharedPreferences{
        return context.getSharedPreferences(FILE_NAME, MODE)
    }
}