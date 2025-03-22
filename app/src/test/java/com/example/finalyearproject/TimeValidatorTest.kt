/*package com.example.finalyearproject

import com.example.finalyearproject.domain.model.TimetableEntry
import com.example.finalyearproject.domain.util.TimeValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TimeValidatorTest {
    private lateinit var validator: TimeValidator

    @Before
    fun setUp() {
        validator = TimeValidator()
    }

    @Test
    fun `test valid time format`() {
        assertTrue(validator.isValidTimeFormat("09:30"))
        assertTrue(validator.isValidTimeFormat("23:59"))
        assertFalse(validator.isValidTimeFormat("24:00"))
        assertFalse(validator.isValidTimeFormat("9:5"))
    }

    @Test
    fun `test time conflict detection`() {
        val existingClasses = listOf(
            TimetableEntry("Math", "08:00", "09:00", "Monday", "Room 101"),
            TimetableEntry("Physics", "10:00", "11:00", "Tuesday", "Lab 202")
        )

        val noConflictClass = TimetableEntry("Biology", "11:30", "12:30", "Monday", "Room 303")
        val conflictingClass = TimetableEntry("History", "08:30", "09:30", "Monday", "Room 102")

        assertTrue(validator.hasNoTimeConflict(existingClasses, noConflictClass))
        assertFalse(validator.hasNoTimeConflict(existingClasses, conflictingClass))
    }
}
*/