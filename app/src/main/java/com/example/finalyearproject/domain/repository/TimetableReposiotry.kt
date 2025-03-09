package com.example.finalyearproject.domain.repository

import com.example.finalyearproject.domain.model.TimetableEntry
import kotlinx.coroutines.flow.Flow

interface TimetableRepository {
    fun getTimetable(): Flow<List<TimetableEntry>>
    suspend fun saveTimetable(timetable: List<TimetableEntry>)
    suspend fun updateTimetableEntry(entry: TimetableEntry)
    suspend fun addCustomTimetableEntry(entry: TimetableEntry) // ✅ Make it suspend
    suspend fun deleteTimetableEntry(entryId: Int) // ✅ Renamed 'i' to 'entryId'
}
