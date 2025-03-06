package com.example.finalyearproject.domain.repository

import com.example.finalyearproject.domain.model.Assignment
import kotlinx.coroutines.flow.Flow

interface AssignmentRepository {
    fun getAssignments(): Flow<List<Assignment>>  // Retrieve all assignments
    fun getUpcomingAssignments(): Flow<List<Assignment>>  // Retrieve only upcoming assignments
    fun getOverdueAssignments(): Flow<List<Assignment>>  // Retrieve overdue assignments
    suspend fun addAssignment(assignment: Assignment)  // Add a new assignment
    suspend fun updateAssignment(assignment: Assignment)  // Update assignment details
    suspend fun deleteAssignment(id: Int)  // Delete an assignment by ID
}
