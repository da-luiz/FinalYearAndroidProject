package com.example.finalyearproject.domain.model

import java.util.Date


data class AssignmentReminder(
    val assignmentId: String,  // Reference to the Assignment
    val assignmentTitle: String,  // Title of the Assignment
    val reminderTime: Date  // When the reminder should be triggered
)