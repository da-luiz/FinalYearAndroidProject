package com.example.finalyearproject.domain.usecase.assignments

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.repository.AssignmentRepository

class MarkAssignmentCompletedUseCase(private val repository: AssignmentRepository) {
    suspend fun execute(assignment: Assignment) {
        val updatedAssignment = assignment.copy(isCompleted = true)
        repository.updateAssignment(updatedAssignment)
    }
}
