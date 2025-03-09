package com.example.finalyearproject.data.repository

import android.content.Context
import com.example.finalyearproject.data.local.TimetableDatabase
import com.example.finalyearproject.data.local.dao.TimetableDao
import com.example.finalyearproject.data.local.entities.TimetableEntity
import com.example.finalyearproject.domain.repository.TimetableRepository
import com.example.finalyearproject.domain.model.TimetableEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TimetableRepositoryImpl(context: Context) : TimetableRepository {

    private val dao: TimetableDao = TimetableDatabase.getDatabase(context).timetableDao()

    override fun getTimetable(): Flow<List<TimetableEntry>> {
        return dao.getTimetable().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveTimetable(timetable: List<TimetableEntry>) {
        dao.clearTimetable()
        dao.insertTimetable(timetable.map { it.toEntity() })
    }

    override suspend fun updateTimetableEntry(entry: TimetableEntry) {
        dao.updateTimetableEntry(entry.toEntity())
    }

    override suspend fun addCustomTimetableEntry(entry: TimetableEntry) {
        dao.insertTimetable(listOf(entry.toEntity()))
    }

    override suspend fun deleteTimetableEntry(entryId: Int) {
        dao.deleteTimetableEntry(entryId)
    }

    private fun TimetableEntity.toDomain() = TimetableEntry(
        courseName = this.courseName,
        startTime = this.startTime,
        endTime = this.endTime,
        dayOfWeek = this.dayOfWeek,
        venue = this.venue
    )

    private fun TimetableEntry.toEntity() = TimetableEntity(
        courseName = this.courseName,
        startTime = this.startTime,
        endTime = this.endTime,
        dayOfWeek = this.dayOfWeek,
        venue = this.venue
    )
}
