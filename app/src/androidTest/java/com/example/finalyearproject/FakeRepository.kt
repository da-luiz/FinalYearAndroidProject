package com.example.finalyearproject

import android.content.Context
import android.net.Uri
import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeRepository : TimetableRepository {

    // ✅ Simulated in-memory database
    private val timetableEntries = mutableListOf<TimetableEntry>()
    private val timetableFlow = MutableStateFlow<List<TimetableEntry>>(emptyList())

    override fun getTimetable(): Flow<List<TimetableEntry>> {
        return timetableFlow.asStateFlow() // ✅ Expose the current state
    }

    override suspend fun saveTimetable(timetable: List<TimetableEntry>) {
        timetableEntries.clear()
        timetableEntries.addAll(timetable)
        timetableFlow.value = timetableEntries
    }

    override suspend fun updateTimetableEntry(entry: TimetableEntry) {
        val index = timetableEntries.indexOfFirst { it.courseName == entry.courseName }
        if (index != -1) {
            timetableEntries[index] = entry
            timetableFlow.value = timetableEntries
        }
    }

    override suspend fun addCustomTimetableEntry(entry: TimetableEntry) {
        timetableEntries.add(entry)
        timetableFlow.value = timetableEntries
    }

    override suspend fun deleteTimetableEntry(entryId: Int) {
        timetableEntries.removeIf { it.hashCode() == entryId }
        timetableFlow.value = timetableEntries
    }

    override suspend fun importTimetableFromExcel(
        context: Context,
        uri: Uri,
        course: String,
        level: String
    ) {
        // ✅ Simulated import logic for tests (does nothing in tests)
    }
}
