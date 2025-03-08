package com.example.finalyearproject.domain.usecase.Timetable

import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow

class GetTimetableUseCase(private val repository: TimetableRepository) {
    fun execute(): Flow<List<TimetableEntry>> {
        return repository.getTimetable()
    }
}
