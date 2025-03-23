package com.example.finalyearproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalyearproject.domain.repository.TimetableRepository

class TimetableViewModelFactory(private val repository: TimetableRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimetableViewModel::class.java)) {
            return TimetableViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
