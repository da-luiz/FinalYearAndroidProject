package com.example.finalyearproject.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel

class DashboardViewModel(private val context: Context) : ViewModel() {
    fun getUsername(): String {
        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("username", "User") ?: "User"
    }
}

