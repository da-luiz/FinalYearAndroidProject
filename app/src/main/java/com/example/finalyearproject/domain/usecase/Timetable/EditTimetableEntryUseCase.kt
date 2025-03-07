package com.example.finalyearproject.domain.usecase.Timetable

import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.repository.TimetableRepository

class EditTimetableEntryUseCase(private val repository: TimetableRepository) {
    suspend fun execute(entry: TimetableEntry) {
        repository.updateTimetableEntry(entry)
    }
}
