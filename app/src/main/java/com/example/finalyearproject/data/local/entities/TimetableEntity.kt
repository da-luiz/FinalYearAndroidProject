package com.example.finalyearproject.data.local.dao

import androidx.room.Entity

@Entity(tableName = "timetable")
data class TimetableEntity(
    val courseName: String,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val venue: String
)
