package com.example.finalyearproject.domain.util

import com.example.finalyearproject.domain.model.TimetableEntry

class TimeValidator {

    fun isValidTimeFormat(time: String): Boolean {
        val pattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]"
        return time.matches(Regex(pattern))
    }

    fun hasNoTimeConflict(classes: List<TimetableEntry>, newClass: TimetableEntry): Boolean {
        return classes.none { existingClass ->
            existingClass.day == newClass.day &&
                    areTimeOverlapping(existingClass, newClass)
        }
    }

    private fun areTimeOverlapping(class1: TimetableEntry, class2: TimetableEntry): Boolean {
        return (class1.endTime > class2.startTime && class1.startTime < class2.endTime)
    }
}
