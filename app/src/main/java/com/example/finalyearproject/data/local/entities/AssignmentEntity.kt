package com.example.finalyearproject.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignments")
data class AssignmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseCode: String,
    val title: String,
    val dueDate: String, // Stored as a timestamp
    val notes: String?,
    val isCompleted: Boolean
)
