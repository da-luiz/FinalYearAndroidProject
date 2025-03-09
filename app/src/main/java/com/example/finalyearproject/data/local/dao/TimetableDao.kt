package com.example.finalyearproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.finalyearproject.data.local.entities.TimetableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDao {
    @Query("SELECT * FROM timetable")
    fun getTimetable(): Flow<List<TimetableEntity>>

    @Insert
    suspend fun insertTimetable(entries: List<TimetableEntity>)

    @Query("DELETE FROM timetable WHERE id = :entryId")
    suspend fun deleteTimetableEntry(entryId: Int)

    @Update
    suspend fun updateTimetableEntry(entity: TimetableEntity)

    @Query("DELETE FROM timetable")
    suspend fun clearTimetable()
}
