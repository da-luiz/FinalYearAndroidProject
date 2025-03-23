package com.example.finalyearproject.domain.model

data class TimetableEntry(
    val day: String,
    val startTime: String,
    val endTime: String,
    val courseName: String,
    val instructor: String,
    val venue: String
)