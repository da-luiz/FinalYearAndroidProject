package com.example.finalyearproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.finalyearproject.domain.model.AssignmentReminder
import com.example.finalyearproject.domain.model.NotificationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

class NotificationViewModel : ViewModel() {
    private val _notifications = MutableStateFlow(
        listOf(
            AssignmentReminder(
                assignmentId = "1",
                assignmentTitle = "Assignment due tomorrow!",
                reminderTime = Date(System.currentTimeMillis()),
                type = NotificationType.ASSIGNMENT // ✅ Now includes 'type'
            ),
            AssignmentReminder(
                assignmentId = "2",
                assignmentTitle = "New timetable available",
                reminderTime = Date(System.currentTimeMillis()),
                type = NotificationType.TIMETABLE // ✅ Now includes 'type'
            )
        )
    )
    val notifications: StateFlow<List<AssignmentReminder>> = _notifications
}
