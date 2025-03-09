package com.example.finalyearproject.domain.usecase.timetable

import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.repository.TimetableRepository

class AddCustomTimetableEntryUseCase(private val repository: TimetableRepository) {
    suspend operator fun invoke(entry: TimetableEntry) {
        repository.addCustomTimetableEntry(entry)
    }
}
