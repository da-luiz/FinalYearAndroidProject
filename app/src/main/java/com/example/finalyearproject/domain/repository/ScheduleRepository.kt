package com.example.finalyearproject.domain.repository

import com.example.finalyearproject.domain.model.Schedule

interface ScheduleRepository {
    suspend fun getSchedules(): List<Schedule>
    suspend fun getScheduleById(id: String): Schedule?
    suspend fun saveSchedule(schedule: Schedule)
    suspend fun deleteSchedule(id: String)
    //fun saveTimetableEntries(entries: kotlin.collections.List<com.example.finalyearproject.domain.model.TimetableEntry>)
    fun saveTimetable(entries: kotlin.collections.List<com.example.finalyearproject.domain.model.TimetableEntry>)
}