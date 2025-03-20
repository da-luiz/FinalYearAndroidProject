package com.example.finalyearproject.data.repository

import android.content.Context
import android.net.Uri
import com.example.finalyearproject.data.excel.ExcelParser
import com.example.finalyearproject.data.local.dao.TimetableDao
import com.example.finalyearproject.data.local.entities.TimetableEntity
import com.example.finalyearproject.domain.repository.TimetableRepository
import com.example.finalyearproject.domain.model.TimetableEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

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

    override suspend fun updateTimetableEntry(entry: TimetableEntry) {
        dao.updateTimetableEntry(entry.toEntity())
    }

    override suspend fun addCustomTimetableEntry(entry: TimetableEntry) {
        dao.insertTimetable(listOf(entry.toEntity()))
    }

    override suspend fun deleteTimetableEntry(entryId: Int) {
        dao.deleteTimetableEntry(entryId) // ✅ Pass only the ID
    }

    override suspend fun importTimetableFromExcel(context: Context, uri: Uri, course: String, level: String) {
        withContext(Dispatchers.IO) {
            try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri) // ✅ Now context is passed
                inputStream?.use {
                    val timetableGrid = ExcelParser.parseExcelFile(it, course, level)
                    val timetableList = mutableListOf<TimetableEntry>()

                    for ((timeSlot, dayEntries) in timetableGrid) {
                        for ((day, details) in dayEntries) {
                            val (courseName, venue) = details.split(" @ ")
                            val (startTime, endTime) = timeSlot.split(" - ")

                            timetableList.add(
                                TimetableEntry(
                                    courseName = courseName,
                                    startTime = startTime,
                                    endTime = endTime,
                                    dayOfWeek = day,
                                    venue = venue
                                )
                            )
                        }
                    }

                    dao.insertTimetable(timetableList.map { it.toEntity() })
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
