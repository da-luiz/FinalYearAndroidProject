package com.example.finalyearproject.data.local.entities

import androidx.room.Entity

@Entity(tableName = "assignments")
data class AssignmentEntity(
    val courseCode: String,
    val title: String,
    val dueDate: Long, // Stored as a timestamp
    val notes: String?,
    val isCompleted: Boolean
)
