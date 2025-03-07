package com.example.finalyearproject.domain.repository

import com.example.finalyearproject.domain.model.TimetableEntry
import kotlinx.coroutines.flow.Flow

interface updateTimetableEntry {
    fun getTimetable(): Flow<List<TimetableEntry>>
    suspend fun saveTimetable(timetable: List<TimetableEntry>)
    suspend fun updateTimetableEntry(entry: TimetableEntry)
}