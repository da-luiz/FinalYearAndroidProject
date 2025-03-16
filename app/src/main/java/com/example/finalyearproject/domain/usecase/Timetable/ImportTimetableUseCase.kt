package com.example.finalyearproject.domain.usecase.Timetable

import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class ImportFilteredTimetableUseCase(private val repository: ScheduleRepository) {

    /**
     * Imports and filters timetable data from an InputStream based on Course Name and Level.
     *
     * @param inputStream The XLSX file InputStream.
     * @param courseName The selected course name.
     * @param level The selected level.
     * @return List of extracted TimetableEntry objects.
     */
    suspend fun execute(
        inputStream: InputStream,
        courseName: String,
        level: String
    ): List<TimetableEntry> {
        return withContext(Dispatchers.IO) {
            val extractedData = ExcelParser.parseExcelFile(inputStream, courseName, level)
            repository.saveTimetable(extractedData as List<TimetableEntry>)  // Save to database (optional)
            extractedData // Return filtered timetable data
        }
    }
}
