package com.example.finalyearproject.domain.usecase.Assignments

import com.example.finalyearproject.domain.model.Assignment
import com.example.finalyearproject.domain.repository.AssignmentRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class GetUpcomingAssignmentsUseCase(private val repository: AssignmentRepository) {
    fun execute(): Flow<List<Assignment>> {
        return repository.getUpcomingAssignments()
    }
}
