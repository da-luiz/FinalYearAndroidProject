package com.example.finalyearproject.domain.usecase.reminder

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.model.AssignmentReminder
import com.example.finalyearproject.domain.model.NotificationType
import com.example.finalyearproject.domain.repository.ReminderRepository
import java.util.Calendar
import java.util.Date

class ScheduleReminderUseCase(private val repository: ReminderRepository) {
    suspend fun execute() {
        val reminder = AssignmentReminder(
            assignmentId = "1",
            assignmentTitle = "Reminder for Assignment",
            reminderTime = Date(System.currentTimeMillis()),  // ✅ Convert Long to Date
            type = NotificationType.ASSIGNMENT
        )
        repository.scheduleReminder(reminder)
    }
}
