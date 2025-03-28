package com.example.finalyearproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finalyearproject.data.local.entities.TimetableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDao {
    @Query("SELECT * FROM timetable ORDER BY day, startTime")
    fun getTimetable(): Flow<List<TimetableEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimetable(entries: List<TimetableEntity>)

    @Query("DELETE FROM timetable")
    suspend fun clearTimetable()

    @Update
    suspend fun updateTimetableEntry(entity: TimetableEntity)

    @Query("DELETE FROM timetable WHERE id = :entryId")
    suspend fun deleteTimetableEntry(entryId: Int)


}