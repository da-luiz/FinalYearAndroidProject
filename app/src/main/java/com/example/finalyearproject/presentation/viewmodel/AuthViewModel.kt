package com.example.finalyearproject.presentation.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    fun saveUsername(context: Context, username: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        sharedPref.edit().putString("username", username).apply()
    }

    fun getUsername(context: Context): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("username", null)
    }
}
