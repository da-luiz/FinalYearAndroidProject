package com.example.finalyearproject.domain.usecase.Reminder

import com.example.finalyearproject.domain.repository.ReminderRepository

class CancelReminderUseCase(private val repository: ReminderRepository) {
    suspend fun execute(assignmentId: Int) {
        repository.cancelReminder(assignmentId.toString())
    }
}