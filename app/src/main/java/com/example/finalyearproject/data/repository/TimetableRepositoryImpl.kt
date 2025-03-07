package com.example.finalyearproject.data.repository

import com.example.finalyearproject.data.local.dao.TimetableDao
import com.example.finalyearproject.data.local.entities.TimetableEntity
import com.example.finalyearproject.domain.repository.TimetableRepository
import com.example.finalyearproject.domain.model.TimetableEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TimetableRepositoryImpl(private val dao: TimetableDao) : TimetableRepository {

    override fun getTimetable(): Flow<List<TimetableEntry>> {
        return dao.getTimetable().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveTimetable(timetable: List<TimetableEntry>) {
        dao.clearTimetable()
        dao.insertTimetable(timetable.map { it.toEntity() })
    }

    private fun TimetableEntity.toDomain() = TimetableEntry(
        id = this.id,
        courseName = this.courseName,
        startTime = this.startTime,
        endTime = this.endTime,
        dayOfWeek = this.dayOfWeek,
        venue = this.venue
    )

    private fun TimetableEntry.toEntity() = TimetableEntity(
        id = this.id,
        courseName = this.courseName,
        startTime = this.startTime,
        endTime = this.endTime,
        dayOfWeek = this.dayOfWeek,
        venue = this.venue
    )
}
