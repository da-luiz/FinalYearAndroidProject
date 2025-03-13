package com.example.finalyearproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalyearproject.domain.models.Timetable
import com.example.finalyearproject.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimetableViewModel(private val repository: TimetableRepository) : ViewModel() {

    private val _timetable = MutableStateFlow<List<Timetable>>(emptyList())  // ✅ Ensure this exists
    val timetable: StateFlow<List<Timetable>> = _timetable  // ✅ Expose it correctly

    init {
        loadTimetable()
    }

    fun loadTimetable() {
        viewModelScope.launch {
            _timetable.value = repository.getAllTimetableEntries() // Fetch data
        }
    }

    fun addTimetableEntry(entry: Timetable) {
        viewModelScope.launch {
            repository.insertTimetableEntry(entry)
            loadTimetable() // Refresh data after adding
        }
    }
}
