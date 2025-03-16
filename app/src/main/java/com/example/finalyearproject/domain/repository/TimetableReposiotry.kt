package com.example.finalyearproject.domain.repository

import com.example.finalyearproject.domain.model.TimetableEntry
import kotlinx.coroutines.flow.Flow

interface TimetableRepository {
    fun getTimetable(): Flow<List<TimetableEntry>>  // Retrieve the timetable
    suspend fun saveTimetable(timetable: List<TimetableEntry>)
    suspend fun updateTimetableEntry(entry: TimetableEntry)  // Edit an existing entry
    suspend fun addCustomTimetableEntry(entry: TimetableEntry)  // Add custom study sessions
    suspend fun deleteTimetableEntry(entry: TimetableEntry)  // Delete an entry
}
