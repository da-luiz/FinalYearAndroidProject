package com.example.finalyearproject.domain.usecase.Assignments

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.repository.AssignmentRepository

class AddAssignmentUseCase(private val repository: AssignmentRepository) {
    suspend fun execute(assignment: Assignment) {
        repository.addAssignment(assignment)
    }
}
