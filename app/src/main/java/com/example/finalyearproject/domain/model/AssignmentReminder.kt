package com.example.finalyearproject.domain.model

enum class NotificationType {
    ASSIGNMENT,
    TIMETABLE,
    GENERAL
}

data class AssignmentReminder(
    val assignmentId: String,  // ✅ Ensure ID is a String
    val assignmentTitle: String,
    val reminderTime: Long,  // ✅ Changed from Date → Long
    val type: NotificationType  // ✅ Ensure type is included
)
