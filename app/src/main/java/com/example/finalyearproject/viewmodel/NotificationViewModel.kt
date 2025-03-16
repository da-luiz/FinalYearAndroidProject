package com.example.finalyearproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.finalyearproject.domain.model.AssignmentReminder
import com.example.finalyearproject.domain.model.NotificationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotificationViewModel : ViewModel() {
    private val _notifications = MutableStateFlow(
        listOf(
            AssignmentReminder(NotificationType.ASSIGNMENT, "Assignment due tomorrow!", System.currentTimeMillis()),
            AssignmentReminder(NotificationType.TIMETABLE, "New timetable available", System.currentTimeMillis())
        )
    )
    val notifications: StateFlow<List<AssignmentReminder>> = _notifications
}
