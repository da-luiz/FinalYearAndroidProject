package com.example.finalyearproject.domain.usecase.assignments

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.repository.AssignmentRepository
import kotlinx.coroutines.flow.Flow

class GetUpcomingAssignmentsUseCase(private val repository: AssignmentRepository) {
    fun execute(): Flow<List<Assignment>> {
        return repository.getUpcomingAssignments()
    }
}
