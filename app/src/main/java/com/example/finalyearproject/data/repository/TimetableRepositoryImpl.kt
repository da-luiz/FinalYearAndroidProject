package com.example.finalyearproject.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                inputStream?.use {
                    val timetableGrid = ExcelParser.parseExcelFile(it, course, level)

                    if (timetableGrid.isEmpty()) {
                        Log.e("TimetableRepository", "❌ Timetable parsing failed! No valid data found.")
                        return@use
                    }

                    val timetableList = mutableListOf<TimetableEntry>()

                    // ✅ Ensure timetableGrid is a Map
                    for ((timeSlot, dayEntries) in timetableGrid) {
                        if (dayEntries is Map<*, *>) { // ✅ Ensure dayEntries is a Map
                            for ((day, details) in dayEntries as Map<String, String>) {
                                val parts = details.split(" @ ")
                                val courseName = parts.getOrNull(0) ?: "Unknown Course"
                                val venue = parts.getOrNull(1) ?: "Unknown Venue"
                                val instructor = parts.getOrNull(2) ?: "Unknown Instructor"

                                val timeParts = timeSlot.split(" - ")
                                val startTime = timeParts.getOrNull(0) ?: "Unknown Start Time"
                                val endTime = timeParts.getOrNull(1) ?: "Unknown End Time"

                                timetableList.add(
                                    TimetableEntry(
                                        courseName = courseName,
                                        startTime = startTime,
                                        endTime = endTime,
                                        day = day,
                                        instructor = instructor,
                                        venue = venue
                                    )
                                )
                            }
                        } else {
                            Log.e("TimetableRepository", "❌ dayEntries is not a Map! Skipping...")
                        }
                    }

                    dao.insertTimetable(timetableList.map { it.toEntity() })
                }
            } catch (e: Exception) {
                Log.e("TimetableRepository", "❌ Error importing timetable: ${e.message}")
                e.printStackTrace()
            }
        }
    }



    private fun TimetableEntity.toDomain() = TimetableEntry(
        courseName = this.courseName,
        startTime = this.startTime,
        endTime = this.endTime,
        day = this.day,
        instructor = this.instructor,  // ✅ Added instructor
        venue = this.venue
    )

    private fun TimetableEntry.toEntity() = TimetableEntity(
        courseName = this.courseName,
        startTime = this.startTime,
        endTime = this.endTime,
        day = this.day,
        instructor = this.instructor,  // ✅ Added instructor
        venue = this.venue
    )
}

@Database(entities = [TimetableEntity::class], version = 1, exportSchema = false)
abstract class TimetableDatabase : RoomDatabase() {
    abstract fun timetableDao(): TimetableDao

    companion object {
        @Volatile
        private var INSTANCE: TimetableDatabase? = null

        fun getInstance(context: Context): TimetableDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimetableDatabase::class.java,
                    "timetable_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
