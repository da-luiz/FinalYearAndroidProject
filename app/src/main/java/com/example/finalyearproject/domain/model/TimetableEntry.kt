package com.example.finalyearproject.domain.model

data class TimetableEntry(
    val courseName: String,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val venue: String,
    val isCustom: Boolean = false
)
