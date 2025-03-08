package com.example.finalyearproject.domain.model

data class Schedule(
    val courseName: String,
    val classes: List<TimetableEntry>
)
