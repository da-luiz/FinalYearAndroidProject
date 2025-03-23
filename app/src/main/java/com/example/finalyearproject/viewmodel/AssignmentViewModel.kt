package com.example.finalyearproject.viewmodel

import com.example.finalyearproject.ui.screens.assignments.Assignment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AssignmentViewModel : ViewModel() {
    private val _assignments = MutableStateFlow(
        listOf(
            Assignment("Computer Science", "Project Proposal", "March 25, 2025"),
            Assignment("English", "Essay on Literature", "March 28, 2025")
        )
    )
    val assignments: StateFlow<List<Assignment>> = _assignments
}
