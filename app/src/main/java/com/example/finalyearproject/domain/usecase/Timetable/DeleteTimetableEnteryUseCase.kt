package com.example.finalyearproject.domain.usecase.Timetable

import com.example.finalyearproject.domain.repository.TimetableRepository

class DeleteTimetableEntryUseCase(private val repository: TimetableRepository) {
    suspend fun execute(entryId: Int) {
        repository.deleteTimetableEntry(entryId)
    }
}
