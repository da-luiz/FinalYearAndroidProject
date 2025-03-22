package com.example.finalyearproject.domain.model

import java.util.Date

data class Assignment(
    val id: String,  // Unique ID
    val courseCode: String,  // e.g., "COSC 201"
    val title: String,  // e.g., "Database Design Project"
    val dueDate: Date,  // Due date & time
    val notes: String?,  // Optional notes
    val isCompleted: Boolean  // Whether it's marked as completed
)