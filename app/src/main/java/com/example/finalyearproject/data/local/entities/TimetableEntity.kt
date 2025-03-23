package com.example.finalyearproject.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable")
data class TimetableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseName: String,
    val startTime: String,
    val endTime: String,
    val day: String,  // ✅ Ensure this column exists
    val instructor: String,  // ✅ Ensure instructor is also added if needed
    val venue: String
)

