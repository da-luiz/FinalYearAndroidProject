package com.example.finalyearproject.domain.usecase.timetable

import com.example.finalyearproject.domain.repository.TimetableRepository
import com.example.finalyearproject.domain.model.TimetableEntry

class UpdateTimetable(private val repository: TimetableRepository) {

    suspend operator fun invoke(entry: TimetableEntry) {
        repository.updateTimetableEntry(entry)
    }
}
