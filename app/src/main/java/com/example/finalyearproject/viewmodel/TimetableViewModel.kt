package com.example.finalyearproject.viewmodel

import TimetableEntry
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimetableViewModel : ViewModel() {
    private val _timetable = MutableStateFlow(
        listOf(
            TimetableEntry("Mathematics", "8:00 AM", "9:30 AM", "Monday", "Room 101"),
            TimetableEntry("Physics", "10:00 AM", "11:30 AM", "Monday", "Lab 3"),
        )
    )
    val timetable: StateFlow<List<TimetableEntry>> = _timetable
}
