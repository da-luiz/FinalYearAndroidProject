package com.example.finalyearproject.domain.usecase.reminder

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.model.AssignmentReminder
import com.example.finalyearproject.domain.repository.ReminderRepository
import java.util.Calendar

class ScheduleReminderUseCase(private val repository: ReminderRepository) {
    suspend fun execute(assignment: Assignment) {
        val reminderTime = Calendar.getInstance().apply {
            time = assignment.dueDate
            add(Calendar.HOUR, -24)  // Set reminder 24 hours before due date
        }.time

        val reminder = AssignmentReminder(
            assignmentId = assignment.id,
            assignmentTitle = assignment.title,
            reminderTime = reminderTime
        )

        repository.scheduleReminder(reminder)
    }
}