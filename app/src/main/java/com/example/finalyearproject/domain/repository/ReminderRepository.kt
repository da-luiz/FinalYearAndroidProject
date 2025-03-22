package com.example.finalyearproject.domain.repository

import com.example.finalyearproject.domain.model.AssignmentReminder

interface ReminderRepository {

    suspend fun scheduleReminder(reminder: AssignmentReminder)
    suspend fun cancelReminder(assignmentId: String)
}