package com.example.finalyearproject.domain.usecase.reminder

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.model.AssignmentReminder
import com.example.finalyearproject.domain.model.NotificationType
import com.example.finalyearproject.domain.repository.ReminderRepository
import java.util.Calendar

class ScheduleReminderUseCase(private val repository: ReminderRepository) {
    suspend fun execute() {
        val reminder = AssignmentReminder(
            assignmentId = "1",  // ✅ Ensure ID is a String
            assignmentTitle = "Reminder for Assignment",
            reminderTime = System.currentTimeMillis(),  // ✅ Ensure reminderTime is Long
            type = NotificationType.ASSIGNMENT  // ✅ Ensure type is included
        )
        repository.scheduleReminder(reminder)
    }
}
