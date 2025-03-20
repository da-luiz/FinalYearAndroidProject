package com.example.finalyearproject.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable")
data class TimetableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseName: String,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val venue: String
)
